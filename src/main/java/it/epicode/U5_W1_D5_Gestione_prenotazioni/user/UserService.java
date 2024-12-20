package it.epicode.U5_W1_D5_Gestione_prenotazioni.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final ObjectProvider<User> userProvider;
    private final UserRepository userRepo;

    public void saveUser() {
        for (int i = 0; i < 20; i++) {
            User user = userProvider.getObject();
            userRepo.save(user);
        }
    }
}
