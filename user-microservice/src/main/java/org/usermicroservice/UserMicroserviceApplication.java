package org.usermicroservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.usermicroservice.entities.Role;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.ERole;
import org.usermicroservice.repositories.RoleRepository;
import org.usermicroservice.services.IUserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class UserMicroserviceApplication {
    private final IUserService userService;
    private final RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            Role userRole = new Role(ERole.USER);
            Role adminRole = new Role(ERole.ADMIN);
            roleRepository.saveAll(List.of(adminRole,userRole));
            Optional<Role> roleUser= roleRepository.findByRole(ERole.USER);
            Optional<Role> roleAdmin= roleRepository.findByRole(ERole.ADMIN);
            User toUser = User.builder()
                    .firstname("Achraf")
                    .lastname("Lamsahel")
                    .email("achraflamsahel1@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .roles(List.of(userRole))
                    .isActive(Active.ACTIVE)
                    .build();

            User u = User.builder()
                    .firstname("Oussama")
                    .lastname("Bernek")
                    .email("oussama@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .roles(roleUser.stream().toList())
                    .isActive(Active.ACTIVE)
                    .build();

            User v = User.builder()
                    .firstname("Kawtar")
                    .lastname("aberdane")
                    .email("kaztar@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .roles(List.of(userRole))
                    .isActive(Active.INACTIVE)
                    .build();

            userService.registerUser(toUser);
            //userService.registerUser(v);
            userService.registerUser(u);
        };

    }


}
