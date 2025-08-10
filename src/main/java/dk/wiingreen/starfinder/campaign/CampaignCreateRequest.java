package dk.wiingreen.starfinder.campaign;

import jakarta.validation.constraints.NotBlank;

public record CampaignCreateRequest(@NotBlank String name) {}
