package it.epicode.U5_W1_D5_Gestione_prenotazioni.runner;


import it.epicode.U5_W1_D5_Gestione_prenotazioni.exception.ReservationException;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.exception.WorkstationException;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation.Reservation;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.reservation.ReservationService;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.User;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.user.UserRepository;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Type;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.WorkstaionService;
import it.epicode.U5_W1_D5_Gestione_prenotazioni.workstation.Workstation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Order(10)
public class ScannerRunner implements ApplicationRunner {
    private Scanner scanner = new Scanner(System.in);
    private final WorkstaionService workstaionService;
    private final ReservationService reservationService;
    private final UserRepository userRepo;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Workstation> workstations;


        while (true) {
            try {
                System.out.println("\n---------- Menu prenotazione ----------\n");
                System.out.println("1. Scegli la città e il tipo di postazione che stai ricercando.");
                System.out.println("0. Esci.");
                System.out.println("\n---------------------------------------\n");

                System.out.println("Digita qui:");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Inserisci la città:");
                        String city = scanner.nextLine();
                        System.out.println("\nInserisci il tipo di postazione tra:");
                        System.out.println("1. Privata");
                        System.out.println("2. Open Space");
                        System.out.println("3. Sala riunioni\n");

                        int type = scanner.nextInt();
                        scanner.nextLine();
                        if (type == 1) {
                            workstations = workstaionService.getWorkstationByCityAndType(city, Type.PRIVATE);
                            System.out.println("Postazioni private nella città " + city + ":");
                            System.out.println("\n---------------------------------------\n");
                            workstations.forEach(System.out::println);
                            System.out.println("\n---------------------------------------\n");
                        } else if (type == 2) {
                            workstations = workstaionService.getWorkstationByCityAndType(city, Type.OPENSPACE);
                            System.out.println("Postazioni open space nella città " + city + ":");
                            System.out.println("\n---------------------------------------\n");
                            workstations.forEach(System.out::println);
                            System.out.println("\n---------------------------------------\n");
                        } else if (type == 3) {
                            workstations = workstaionService.getWorkstationByCityAndType(city, Type.MEETING_ROOM);
                            System.out.println("Postazioni sala riunioni nella città " + city + ":");
                            System.out.println("\n---------------------------------------\n");
                            workstations.forEach(System.out::println);
                            System.out.println("\n---------------------------------------\n");
                        } else {
                            throw new ReservationException("Scelta non valida");
                        }
                        System.out.println("Scegli la postazione tramite il suo id:");
                        Long id = scanner.nextLong();
                        scanner.nextLine();
                        Workstation workstation = workstations.stream()
                                .filter(w -> w.getId().equals(id))
                                .findFirst()
                                .orElse(null);
                        if (workstation == null) {
                            throw new WorkstationException("Postazione non trovata");
                        }
                        System.out.println("Fra quanti giori vuoi prenotare?");
                        int days = scanner.nextInt();
                        scanner.nextLine();
                        LocalDate date = LocalDate.now().plusDays(days);

                        System.out.println("Inserisci il tuo id utente tra questi:");
                        List<User> users = userRepo.findAll();
                        System.out.println("\n---------------------------------------\n");
                        users.forEach(System.out::println);
                        System.out.println("\n---------------------------------------\n");
                        Long userId = scanner.nextLong();
                        scanner.nextLine();
                        User user = users.stream()
                                .filter(u -> u.getId().equals(userId))
                                .findFirst()
                                .orElse(null);
                        if (user == null) {
                            throw new WorkstationException("Utente non trovato");
                        }
                       Reservation res = reservationService.makeReservation(user, workstation, date);
                        if (res != null) {
                            System.out.println("\n---------------------------------------\n");
                            System.out.println("Prenotazione effettuata con successo!");
                            System.out.println(res);
                            System.out.println("\n---------------------------------------\n");
                        }
                    }
                    case 0 -> {
                        System.exit(0);
                    }
                    default -> System.err.println("Scelta non valida.");

                }

            } catch (ReservationException e) {
                System.err.println(e.getMessage());
            } catch (WorkstationException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }


    }
}