package dk.wiingreen.starfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dk.wiingreen.starfinder.campaign.CampaignRepository;
import jakarta.transaction.Transactional;
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

  @Autowired
  CampaignIntegrationTests(MockMvc mockMvc, CampaignRepository campaignRepository) {
    this.mockMvc = mockMvc;
    this.campaignRepository = campaignRepository;
  }

  @Test
  @WithMockUser("testuser")
  public void campaignIsSavedForLoggedInUserWhenFormIsSubmitted() throws Exception {
    mockMvc
        .perform(post("/campaigns").param("name", "The Forgotten Void").with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/campaigns"));

    assertThat(campaignRepository.findAll()).singleElement();
  }
}
