package it.epicode.U5_W1_D5_Gestione_prenotazioni.building;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BuildingConfig {
    private final Faker faker;
    private final List<String> cities;

    @Bean
    @Scope("prototype")
    public Building newBuilding() {
        Building b = new Building();
        b.setName(faker.company().name());
        b.setAddress(faker.address().streetAddress());
        b.setCity(cities.get(faker.number().numberBetween(0, cities.size())));
        return b;
    }
}
