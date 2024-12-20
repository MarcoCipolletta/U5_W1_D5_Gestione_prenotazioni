package it.epicode.U5_W1_D5_Gestione_prenotazioni.runner;


import it.epicode.U5_W1_D5_Gestione_prenotazioni.service.BuildingAndWorkstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class BuildingAndWorkstationRunner implements ApplicationRunner {
    private final BuildingAndWorkstationService buildingAndWorkstationService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        buildingAndWorkstationService.saveBuildingWithWorkstation();
    }
}