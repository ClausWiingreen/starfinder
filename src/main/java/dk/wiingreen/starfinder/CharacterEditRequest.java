package dk.wiingreen.starfinder;

import jakarta.validation.constraints.NotBlank;

record CharacterEditRequest(
        @NotBlank(message = "Name is required") String name) {
}
