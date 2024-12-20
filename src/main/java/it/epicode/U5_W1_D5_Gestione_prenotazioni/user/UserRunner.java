package it.epicode.U5_W1_D5_Gestione_prenotazioni.user;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class UserRunner implements ApplicationRunner {
        private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userService.saveUser();

    }
}