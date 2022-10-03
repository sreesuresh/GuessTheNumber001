package com.example.guessthenumber001;

import com.example.guessthenumber001.service.GameRepository;
import com.example.guessthenumber001.service.RoundRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log
@SpringBootApplication
public class GuessTheNumber001Application {

    public static void main(String[] args) {
        SpringApplication.run(GuessTheNumber001Application.class, args);

        System.out.println("Guess the Number Application");
    }

    @Bean
    public CommandLineRunner demo(GameRepository appUserRepository,
                                  RoundRepository questionRepository) {
        return (args) -> {
            log.info("Demo is working");

        };
    }
}
