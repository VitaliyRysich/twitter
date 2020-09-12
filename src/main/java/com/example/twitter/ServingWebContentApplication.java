package com.example.twitter;

import com.example.twitter.domain.Role;
import com.example.twitter.domain.User;
import com.example.twitter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
public class ServingWebContentApplication implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User admin = new User("adm","adm", true);
        admin.setRoles(new HashSet<>(
                Arrays.asList(
                        Role.USER,
                        Role.ADMIN)
        ));
        userRepo.save(admin);

        User user = new User("usr","usr", true);
        user.setRoles(new HashSet<>(
                Arrays.asList(
                        Role.USER)
        ));
        userRepo.save(user);
    }
}

