package dk.wiingreen.starfinder.campaign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {
  @PostMapping
  String createCampaign() {
    return "redirect:/campaigns";
  }
}
