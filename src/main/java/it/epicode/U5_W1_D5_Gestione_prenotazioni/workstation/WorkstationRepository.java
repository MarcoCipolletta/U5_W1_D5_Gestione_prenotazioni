package it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WorkstationRepository extends JpaRepository<Workstation, Long> {
    List<Workstation> findByTypeAndBuildingCityIgnoreCase(Type type, String city);
}
