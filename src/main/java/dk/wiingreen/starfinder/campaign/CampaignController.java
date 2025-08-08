package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.CurrentUserService;
import dk.wiingreen.starfinder.auth.User;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {
  private final CampaignRepository campaignRepository;
  private final CurrentUserService currentUserService;

  public CampaignController(
      CampaignRepository campaignRepository, CurrentUserService currentUserService) {
    this.campaignRepository = campaignRepository;
    this.currentUserService = currentUserService;
  }

  @PostMapping
  String createCampaign(String name) {
    Optional<User> currentUser = currentUserService.getCurrentUser();
    campaignRepository.save(new Campaign(name, currentUser.orElse(null)));
    return "redirect:/campaigns";
  }
}
