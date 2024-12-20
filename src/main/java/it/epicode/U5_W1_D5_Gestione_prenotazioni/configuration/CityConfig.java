package it.epicode.U5_W1_D5_Gestione_prenotazioni.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CityConfig {

    @Bean
    public List<String> getCities() {
        return List.of("Milano", "Roma", "Napoli", "Torino", "Firenze", "Palermo");
    }
}
