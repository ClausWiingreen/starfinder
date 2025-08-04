package dk.wiingreen.starfinder.auth;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
class AuthController {
  private static final Logger log = LoggerFactory.getLogger(AuthController.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/register")
  String getRegisterUserForm(Model model) {
    model.addAttribute("registerUserRequest", new RegisterUserRequest("", ""));
    return "/auth/register";
  }

  @PostMapping("/register")
  String registerUser(
      @Valid @ModelAttribute RegisterUserRequest registerUserRequest, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "/auth/register";
    }

    log.info("Registering user <{}>", registerUserRequest.username());

    if (userRepository.existsByUsername(registerUserRequest.username())) {
      log.error("User already exists <{}>", registerUserRequest.username());
      bindingResult.rejectValue("username", "username.exists", "Username already exists");
      return "/auth/register";
    }
    userRepository.save(
        new User(
            registerUserRequest.username(),
            passwordEncoder.encode(registerUserRequest.password())));
    return "redirect:/login";
  }
}
