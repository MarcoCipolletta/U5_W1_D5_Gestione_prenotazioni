package it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.exception.ReservationException;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.User;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.UserRepository;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.WorkstationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final UserRepository userRepo;
    private final WorkstationRepository workstationRepo;

    @Transactional
    public Reservation makeReservation(User user, Workstation workstation, LocalDate date) throws ReservationException {
        List<Reservation> reservationsInThatDate = reservationRepo.findReservationsByWorkstationAndDate(workstation.getId(),date);
        if (reservationsInThatDate.size() >= workstation.getMaxOccupants()) {
            throw new ReservationException("La postazione è già piena");
        }
        Reservation reservationAlreadyExistsForThatDate = reservationRepo.findReservationByUserAndDate(user.getId(), date);
        if (reservationAlreadyExistsForThatDate != null) {
            throw new ReservationException("Hai già una prenotazione per quella data");
        }

        Reservation r = new Reservation();
        r.setUser(user);
        r.setWorkstation(workstation);
        r.setDate(date);
        r.setValid(true);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfDay = r.getDate().atTime(23, 59, 59);
        long delay = ChronoUnit.MILLIS.between(now, endOfDay);

        executor.schedule(() -> {
            System.out.println("Prenotazione scaduta");
            r.setValid(false);
            reservationRepo.save(r);
        }, delay,TimeUnit.MILLISECONDS);

        reservationRepo.save(r);
        workstation.getReservations().add(r);
        user.getReservations().add(r);
        return r;
    }
}
