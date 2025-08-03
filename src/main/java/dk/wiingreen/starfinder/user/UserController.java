package dk.wiingreen.starfinder.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    String registerUser(RegistrationForm form, BindingResult bindingResult) {
        log.info("Registering user <{}>", form.username());
        if (userRepository.existsByUsername(form.username())) {
            log.error("User already exists <{}>", form.username());
            bindingResult.rejectValue("username", "username.exists");
            return "/index";
        }
        userRepository.save(new User(form.username(), passwordEncoder.encode(form.password())));
        return "redirect:/login";
    }
}
