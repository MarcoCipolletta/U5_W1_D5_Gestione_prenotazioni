package it.epicode.U5_W1_D5_Gestione_prenotazioni.building;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.jdbc.Work;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    private String city;
    @OneToMany(mappedBy = "building")
    private List<Workstation> workstations = new ArrayList<>();


}
