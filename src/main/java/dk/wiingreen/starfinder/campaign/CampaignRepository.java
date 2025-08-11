package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRepository extends CrudRepository<Campaign, UUID> {
  Optional<Campaign> findByIdAndOwner(UUID id, User owner);
}
