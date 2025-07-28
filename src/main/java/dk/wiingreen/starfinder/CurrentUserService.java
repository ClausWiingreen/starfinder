package dk.wiingreen.starfinder;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CurrentUserService {
    private final UserRepository userRepository;

    CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Optional<User> getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return Optional.empty();
        }

        var username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
