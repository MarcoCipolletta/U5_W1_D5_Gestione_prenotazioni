package it.epicode.U5_W1_D5_Gestione_prenotazioni.service;



import com.github.javafaker.Faker;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.building.Building;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.building.BuildingRepository;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.WorkstationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuildingAndWorkstationService {
    private final ObjectProvider<Building> userProvider;
    private final ObjectProvider<Workstation> workstationProvider;
    private final BuildingRepository buildingRepo;
    private final WorkstationRepository workstationRepo;
    private final Faker faker;

    @Transactional
    public void saveBuildingWithWorkstation() {
        for (int i = 0; i < 50; i++) {
        Building b = userProvider.getObject();
            buildingRepo.save(b);
            for (int j = 0; j < faker.number().numberBetween(5,15); j++) {
                Workstation w = workstationProvider.getObject();
                w.setBuilding(b);
                b.getWorkstations().add(w);
                workstationRepo.save(w);

            }
        }
    }


}
