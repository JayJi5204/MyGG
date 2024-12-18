package com.project.mygg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyGGApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGGApplication.class, args);
    }

}
