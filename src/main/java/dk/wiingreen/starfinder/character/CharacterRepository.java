package dk.wiingreen.starfinder.character;

import dk.wiingreen.starfinder.auth.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository extends CrudRepository<Character, UUID> {
    Optional<Character> findByIdAndOwner(UUID id, User owner);

    Iterable<Character> findByOwner(User owner);

    void deleteByIdAndOwner(UUID id, User owner);
}
