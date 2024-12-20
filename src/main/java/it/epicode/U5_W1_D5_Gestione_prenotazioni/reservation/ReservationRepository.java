package it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r JOIN r.workstation w WHERE w.id = :workstationId AND r.date = :date")
    List<Reservation> findReservationsByWorkstationAndDate( Long workstationId, LocalDate date);

    @Query("SELECT r FROM Reservation r JOIN r.user u WHERE u.id = :userId AND r.date = :date")
    Reservation findReservationByUserAndDate(Long userId, LocalDate date);
}
