package it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation;

import it.epicode.U5_W1_D5_Gestione_prenotazioni.exception.WorkstationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkstaionService {
    private final WorkstationRepository workstationRepository;
    private final List<String> cities;

    public List<Workstation> getWorkstationByCityAndType(String city, Type type) throws WorkstationException {
        boolean containsIgnoreCase = cities.stream()
                .anyMatch(c -> c.equalsIgnoreCase(city));
        if (containsIgnoreCase) {
            return workstationRepository.findByTypeAndBuildingCityIgnoreCase(type, city);
        } else {
            throw new WorkstationException("Non abbiamo una postazione in quella città");
        }

    }
}
