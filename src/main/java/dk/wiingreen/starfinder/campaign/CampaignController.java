package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.CurrentUserService;
import dk.wiingreen.starfinder.auth.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CampaignController.CAMPAIGN_PATH)
public class CampaignController {
  static final String CAMPAIGN_PATH = "/campaigns";
  private static final String CREATE_SUBPATH = "/new";

  private final CampaignRepository campaignRepository;
  private final CurrentUserService currentUserService;

  public CampaignController(
      CampaignRepository campaignRepository, CurrentUserService currentUserService) {
    this.campaignRepository = campaignRepository;
    this.currentUserService = currentUserService;
  }

  @GetMapping
  public String getCampaignOverview() {
    return "/campaigns/overview";
  }

  @GetMapping(CREATE_SUBPATH)
  public String getCreateCampaignForm(Model model) {
    model.addAttribute("campaignCreateRequest", new CampaignCreateRequest(""));
    return "/campaigns/new";
  }

  @PostMapping(CREATE_SUBPATH)
  String createCampaign(
      @Valid CampaignCreateRequest campaignCreateRequest, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/campaigns/new";
    }
    User currentUser = currentUserService.getCurrentUserOrThrow();
    campaignRepository.save(new Campaign(campaignCreateRequest.name(), currentUser));
    return "redirect:/campaigns";
  }
}
