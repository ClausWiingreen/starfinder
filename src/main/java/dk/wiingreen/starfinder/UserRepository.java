package dk.wiingreen.starfinder;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
