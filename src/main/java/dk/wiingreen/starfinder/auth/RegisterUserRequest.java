package dk.wiingreen.starfinder.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record RegisterUserRequest(
    @NotBlank(message = "Username is required") String username,
    @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,
    String repeatPassword) {

  @Override
  public String toString() {
    return "RegisterUserRequest{username='%s'}".formatted(password);
  }
}
