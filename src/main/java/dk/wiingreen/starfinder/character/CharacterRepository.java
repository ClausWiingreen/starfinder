package dk.wiingreen.starfinder.character;

import dk.wiingreen.starfinder.auth.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, UUID> {
  Optional<Character> findByIdAndOwner(UUID id, User owner);

  Iterable<Character> findByOwner(User owner);

  void deleteByIdAndOwner(UUID id, User owner);
}
