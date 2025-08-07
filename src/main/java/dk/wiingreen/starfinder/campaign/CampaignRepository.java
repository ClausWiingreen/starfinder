package dk.wiingreen.starfinder.campaign;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CampaignRepository extends CrudRepository<Campaign, UUID> {}
