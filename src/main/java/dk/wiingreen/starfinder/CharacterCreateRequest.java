package dk.wiingreen.starfinder;

import jakarta.validation.constraints.NotBlank;

record CharacterCreateRequest(
        @NotBlank(message = "Name is required") String name) {
}
