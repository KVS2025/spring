package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Starting point. Spring Boot starts the IoC container AND a web server (Tomcat).
// Then open http://localhost:8080 in your browser.
@SpringBootApplication
public class LabWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabWebApplication.class, args);
    }
}
