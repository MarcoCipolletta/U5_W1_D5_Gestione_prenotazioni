package it.epicode.U5_W1_D5_Gestione_prenotazioni.user;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();


}
