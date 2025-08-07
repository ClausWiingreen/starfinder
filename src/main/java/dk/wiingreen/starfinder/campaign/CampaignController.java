package dk.wiingreen.starfinder.campaign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {
  private final CampaignRepository campaignRepository;

  public CampaignController(CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }

  @PostMapping
  String createCampaign() {
    campaignRepository.save(new Campaign());
    return "redirect:/campaigns";
  }
}
