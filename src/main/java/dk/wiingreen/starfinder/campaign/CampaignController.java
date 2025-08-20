package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.CurrentUserService;
import dk.wiingreen.starfinder.auth.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(CampaignController.CAMPAIGN_PATH)
class CampaignController {
  static final String CAMPAIGN_PATH = "/campaigns";
  private static final String CREATE_SUBPATH = "/new";
  private static final Logger log = LoggerFactory.getLogger(CampaignController.class);

  private final CampaignRepository campaignRepository;
  private final CurrentUserService currentUserService;

  CampaignController(CampaignRepository campaignRepository, CurrentUserService currentUserService) {
    this.campaignRepository = campaignRepository;
    this.currentUserService = currentUserService;
  }

  private static Supplier<ModelAndView> campaignNotFound(UUID id) {
    return () ->
        new ModelAndView(
            "/error",
            Map.of(
                "status",
                404,
                "error",
                "Campaign not found",
                "message",
                "Failed to find campaign with id %s".formatted(id)),
            HttpStatusCode.valueOf(404));
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
    log.info("Creating campaign: {}", campaignCreateRequest);
    campaignRepository.save(new Campaign(campaignCreateRequest.name(), currentUser));
    return "redirect:/campaigns";
  }

  @GetMapping("/{id}")
  ModelAndView getCampaign(@PathVariable UUID id, Model model) {
    var user = currentUserService.getCurrentUserOrThrow();
    return campaignRepository
        .findByIdAndOwner(id, user)
        .map(
            campaign ->
                new ModelAndView(
                    "/campaigns/edit",
                    Map.of(
                        "campaignEditRequest",
                        new CampaignEditRequest(campaign.getName()),
                        "id",
                        campaign.getId())))
        .orElseGet(campaignNotFound(id));
  }

  @PostMapping("/{id}")
  ModelAndView editCampaign(
      @PathVariable UUID id,
      @Valid @ModelAttribute CampaignEditRequest campaignEditRequest,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ModelAndView("/campaigns/edit");
    }
    var user = currentUserService.getCurrentUserOrThrow();
    return campaignRepository
        .findByIdAndOwner(id, user)
        .map(
            campaign -> {
              if (!campaign.getName().equals(campaignEditRequest.name())) {
                log.info(
                    "Updating campaign with id <{}> name from <{}> to <{}>",
                    id,
                    campaign.getName(),
                    campaignEditRequest.name());
                campaign.setName(campaignEditRequest.name());
              }
              campaignRepository.save(campaign);
              return new ModelAndView("redirect:/campaigns");
            })
        .orElseGet(campaignNotFound(id));
  }

  @PostMapping("/{id}/delete")
  @Transactional
  String deleteCampaign(@PathVariable UUID id) {
    log.info("Trying to delete campaign with id {}", id);
    var user = currentUserService.getCurrentUserOrThrow();
    campaignRepository.deleteByIdAndOwner(id, user);
    return "redirect:/campaigns";
  }
}
