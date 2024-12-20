package it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.building.Building;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "workstations")
public class Workstation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "unique_code" ,unique = true, nullable = false)
    private String uniqueCode;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "max_occupants")
    private int maxOccupants;
    @ManyToOne
    @ToString.Exclude
    private Building building;
    @OneToMany(mappedBy = "workstation",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}
