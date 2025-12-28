package com.example.SpringConcepts.config;

import com.example.SpringConcepts.entity.Users;
import com.example.SpringConcepts.repository.UserRepo;
import com.example.SpringConcepts.role_permissions.ROLE;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultRunner {

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepo.existsById("mahesh123T")) {

                Users user = new Users(
                        "mahesh123T",
                        passwordEncoder.encode("mahi@123")
                );

                user.setRole(ROLE.ADMIN);

                userRepo.save(user);
                System.out.println("User 'mahesh123T' created");
            } else {
                System.out.println("User 'mahesh123T' already exists");
            }
        };
    }
}
