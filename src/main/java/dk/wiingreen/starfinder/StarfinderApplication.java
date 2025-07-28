package dk.wiingreen.starfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
class StarfinderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarfinderApplication.class, args);
    }
}
