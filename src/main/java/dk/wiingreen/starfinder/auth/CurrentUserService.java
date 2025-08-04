package dk.wiingreen.starfinder.auth;

import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
  private final UserRepository userRepository;

  CurrentUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getCurrentUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || !authentication.isAuthenticated()
        || "anonymousUser".equals(authentication.getPrincipal())) {
      return Optional.empty();
    }

    var username = authentication.getName();
    return userRepository.findByUsername(username);
  }
}
