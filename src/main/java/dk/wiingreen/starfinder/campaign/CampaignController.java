package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.CurrentUserService;
import dk.wiingreen.starfinder.auth.User;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CampaignController.CAMPAIGN_PATH)
class CampaignController {
  static final String CAMPAIGN_PATH = "/campaigns";
  private static final String CREATE_SUBPATH = "/new";

  private final CampaignRepository campaignRepository;
  private final CurrentUserService currentUserService;

  CampaignController(CampaignRepository campaignRepository, CurrentUserService currentUserService) {
    this.campaignRepository = campaignRepository;
    this.currentUserService = currentUserService;
  }

  @GetMapping
  String getCampaignOverview(Model model) {
    User currentUser = currentUserService.getCurrentUserOrThrow();
    model.addAttribute("campaigns", campaignRepository.findAllByOwner(currentUser));
    return "/campaigns/overview";
  }

  @GetMapping(CREATE_SUBPATH)
  String getCreateCampaignForm(Model model) {
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

  @GetMapping("/{id}")
  String getCampaign(@PathVariable UUID id, Model model) {
    var user = currentUserService.getCurrentUserOrThrow();
    return campaignRepository
        .findByIdAndOwner(id, user)
        .map(
            campaign -> {
              model.addAttribute("campaign", campaign);
              return "/campaigns/edit";
            })
        .orElseGet(
            () -> {
              model.addAttribute("status", 404);
              model.addAttribute("error", "Campaign not found");
              model.addAttribute("message", "Failed to find campaign with id %s".formatted(id));
              return "/error";
            });
  }

  @PostMapping("/{id}")
  String editCampaign(
      @PathVariable UUID id,
      @Valid @ModelAttribute CampaignEditRequest campaignEditRequest,
      BindingResult bindingResult) {
    return "/campaigns/edit";
  }
}
