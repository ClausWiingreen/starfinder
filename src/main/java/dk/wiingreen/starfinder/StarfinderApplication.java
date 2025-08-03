package dk.wiingreen.starfinder;

import dk.wiingreen.starfinder.auth.User;
import dk.wiingreen.starfinder.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
class StarfinderApplication {
    private static final Logger log = LoggerFactory.getLogger(StarfinderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StarfinderApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, @Value("${dk.wiingreen.starfinder.testusers}") String[] testusers) {
        return args -> {
            log.info("Seeding users... {}", (Object) testusers);
            userRepository.deleteAll();
            var users = Arrays.stream(testusers)
                    .map(username -> new User(username, "{noop}password"))
                    .toList();
            userRepository.saveAll(users);
        };
    }
}
