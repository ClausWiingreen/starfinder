package dk.wiingreen.starfinder;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharacterRepository extends CrudRepository<Character, UUID> {
}
