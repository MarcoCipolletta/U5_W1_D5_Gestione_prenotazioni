package it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class WorkstationConfig {
    private final Faker faker;

    @Bean
    @Scope("prototype")
    public Workstation newWorkstation() {
        Workstation w = new Workstation();
        w.setUniqueCode(faker.code().ean13());
        w.setDescription(faker.lorem().sentence());
        w.setUniqueCode("WS-" + faker.code().ean13());
        w.setType(Type.values()[faker.number().numberBetween(0, 3)]);
        if (w.getType() == Type.OPENSPACE) {
        w.setMaxOccupants(faker.number().numberBetween(5, 10));
        } else if (w.getType() == Type.PRIVATE) {
        w.setMaxOccupants(1);
        } else {
        w.setMaxOccupants(faker.number().numberBetween( 15, 30));
        }
        return w;
    }
}