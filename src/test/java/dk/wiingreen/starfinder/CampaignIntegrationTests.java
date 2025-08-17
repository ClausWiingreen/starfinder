package dk.wiingreen.starfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import dk.wiingreen.starfinder.auth.User;
import dk.wiingreen.starfinder.auth.UserRepository;
import dk.wiingreen.starfinder.campaign.Campaign;
import dk.wiingreen.starfinder.campaign.CampaignRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CampaignIntegrationTests {
  private final MockMvc mockMvc;
  private final CampaignRepository campaignRepository;
  private final UserRepository userRepository;

  @Autowired
  CampaignIntegrationTests(
      MockMvc mockMvc, CampaignRepository campaignRepository, UserRepository userRepository) {
    this.mockMvc = mockMvc;
    this.campaignRepository = campaignRepository;
    this.userRepository = userRepository;
  }

  @Test
  @WithMockUser("testuser")
  void campaignIsSavedForLoggedInUserWhenFormIsSubmitted() throws Exception {
    userRepository.save(new User("testuser", null));

    mockMvc
        .perform(post("/campaigns/new").param("name", "The Forgotten Void").with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/campaigns"));

    assertThat(campaignRepository.findAll())
        .singleElement()
        .extracting("name", "owner.username")
        .containsExactly("The Forgotten Void", "testuser");
  }

  @Test
  @WithMockUser("testuser")
  void campaignFormRejectsEmptyName() throws Exception {
    mockMvc
        .perform(post("/campaigns/new").param("name", "").with(csrf()))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("campaignCreateRequest", "name"))
        .andExpect(view().name("/campaigns/new"));
  }

  @Test
  @WithMockUser("intruder")
  void userCannotAccessOtherUsersCampaign() throws Exception {
    userRepository.save(new User("intruder", null));
    var owner = userRepository.save(new User("owner", null));
    var campaign = campaignRepository.save(new Campaign("campaignName", owner));

    mockMvc
        .perform(get("/campaigns/%s".formatted(campaign.getId())))
        .andExpect(status().isOk())
        .andExpect(view().name("/error"))
        .andExpect(model().attribute("status", 404));
  }

  @Test
  @WithMockUser("alice")
  void campaignNameIsUpdatedWhenEditFormIsSubmitted() throws Exception {
    var alice = userRepository.save(new User("alice", null));
    var bob = userRepository.save(new User("bob", null));

    campaignRepository.saveAll(
        List.of(
            new Campaign("A—Drift in the Vast", alice),
            new Campaign("A—Signal of Screams", alice),
            new Campaign("A—Dead Suns", bob)));

    var result =
        mockMvc
            .perform(get("/campaigns"))
            .andExpect(status().isOk())
            .andExpect(view().name("/campaigns/overview"))
            .andExpect(model().attributeExists("campaigns"))
            .andReturn();

    var modelAndView = result.getModelAndView();
    assertThat(modelAndView)
        .isNotNull()
        .extracting("model.campaigns", InstanceOfAssertFactories.list(String.class))
        .extracting("name")
        .containsExactlyInAnyOrder("A—Drift in the Vast", "A—Signal of Screams");
  }

  @Test
  @WithMockUser("owner")
  void campaignEditFormRejectsEmptyName() throws Exception {
    var campaign = campaignRepository.save(new Campaign("Original Campaign", null));
    mockMvc
        .perform(post("/campaigns/{id}", campaign.getId()).param("name", "").with(csrf()))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("campaignEditRequest", "name"))
        .andExpect(view().name("/campaigns/edit"));
  }
}
