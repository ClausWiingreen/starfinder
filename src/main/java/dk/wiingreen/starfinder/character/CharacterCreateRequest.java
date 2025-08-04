package dk.wiingreen.starfinder.character;

import jakarta.validation.constraints.NotBlank;

record CharacterCreateRequest(@NotBlank(message = "Name is required") String name) {}
