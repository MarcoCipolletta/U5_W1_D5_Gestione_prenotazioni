package it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.User;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "is_valid")
    private boolean isValid;
    private LocalDate date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Workstation workstation;

}
