package com.example.spring_boot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.time.LocalDate;
import java.time.Month;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    LocalDate.of(2000, JANUARY, 5),
                    "mariam@gmail.com"
            );

            Student akanksha = new Student(
                    "Akanksha",
                    LocalDate.of(2003, JANUARY, 17),
                    "alex@gmail.com"
            );

            repository.saveAll(
                    List.of(mariam, akanksha)
            );
        };
    }
}
