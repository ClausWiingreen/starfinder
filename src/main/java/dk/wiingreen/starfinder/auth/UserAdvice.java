package dk.wiingreen.starfinder.auth;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdvice {
  private final CurrentUserService currentUserService;

  public UserAdvice(CurrentUserService currentUserService) {
    this.currentUserService = currentUserService;
  }

  @ModelAttribute("currentUser")
  User getCurrentUser() {
    return currentUserService.getCurrentUser().orElse(null);
  }
}
