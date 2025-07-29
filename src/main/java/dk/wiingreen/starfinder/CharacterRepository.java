package dk.wiingreen.starfinder;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

interface CharacterRepository extends CrudRepository<Character, UUID> {
    Optional<Character> findByIdAndOwner(UUID id, User owner);

    Iterable<Character> findByOwner(User owner);
}
