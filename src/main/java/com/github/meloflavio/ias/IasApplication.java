package com.github.meloflavio.ias;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IasApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
