package dk.wiingreen.starfinder.auth;

import jakarta.validation.constraints.NotBlank;

record RegisterUserRequest(
        String username,
        @NotBlank(message = "Password is required") String password) {
    @Override
    public String toString() {
        return "RegisterUserRequest{username='%s'}".formatted(password);
    }
}
