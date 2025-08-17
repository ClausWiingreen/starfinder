package dk.wiingreen.starfinder.campaign;

import jakarta.validation.constraints.NotBlank;

public record CampaignEditRequest(@NotBlank String name) {}
