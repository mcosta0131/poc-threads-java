package com.example.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class PocThreadsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocThreadsApplication.class, args);
    }
}
