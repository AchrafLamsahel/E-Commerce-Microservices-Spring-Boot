package org.usermicroservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.usermicroservice.entities.ConfirmationToken;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.Role;
import org.usermicroservice.repositories.ConfirmationTokenRepository;
import org.usermicroservice.services.IUserService;


@SpringBootApplication
@AllArgsConstructor
public class UserMicroserviceApplication {
    private final IUserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            User toUser = User.builder()
                    .firstname("Achraf")
                    .lastname("Lamsahel")
                    .email("achraflamsahel1@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .role(Role.ADMIN)
                    .isActive(Active.ACTIVE)
                    .build();

            User u = User.builder()
                    .firstname("Oussama")
                    .lastname("Bernek")
                    .email("oussama@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .role(Role.USER)
                    .isActive(Active.ACTIVE)
                    .build();

            User v = User.builder()
                    .firstname("Kawtar")
                    .lastname("aberdane")
                    .email("kaztar@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .role(Role.ADMIN)
                    .isActive(Active.INACTIVE)
                    .build();

            userService.registerUser(toUser);
            userService.registerUser(v);
            userService.registerUser(u);
        };

    }


}
