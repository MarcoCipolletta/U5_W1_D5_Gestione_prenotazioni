package it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation;


import it.epicode.U5_W1_D5_Gestione_prenotazioni.exception.ReservationException;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.User;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.UserRepository;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Type;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.WorkstationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(3)
public class ReservationRunner implements ApplicationRunner {

    private final ReservationService reservationService;
    private final UserRepository userRepo;
    private final WorkstationRepository workstationRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("Utente non trovato"));;
        Workstation workstation = workstationRepo.findById(1L).orElseThrow(() -> new RuntimeException("Postazione non trovata"));

        try{
        reservationService.makeReservation(user, workstation, LocalDate.now());

        }catch (ReservationException e){
            System.err.println(e.getMessage());
        }



    }
}