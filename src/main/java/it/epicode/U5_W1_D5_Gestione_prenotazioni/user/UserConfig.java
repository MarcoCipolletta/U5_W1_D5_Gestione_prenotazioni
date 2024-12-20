package it.epicode.U5_W1_D5_Gestione_prenotazioni.user;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final Faker faker;


    @Bean(name = "user")
    @Scope("prototype")
    public User newUser() {
        User u = new User();
        u.setUsername(faker.name().username());
        u.setFullName(faker.name().fullName());
        u.setEmail(faker.internet().emailAddress());
        return u;
    }
}
