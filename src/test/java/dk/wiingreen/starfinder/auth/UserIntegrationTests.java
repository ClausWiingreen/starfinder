package dk.wiingreen.starfinder.auth;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTests {
    private final UserRepository userRepository;
    private final MockMvc mockMvc;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserIntegrationTests(UserRepository userRepository, MockMvc mockMvc, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mockMvc = mockMvc;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void newUserCanRegisterAndLoginSuccessfully() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("username", "newuser")
                        .param("password", "securePass123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        var maybeUser = userRepository.findByUsername("newuser");
        assertThat(maybeUser).hasValueSatisfying(matchesPassword("securePass123"));
    }

    @Test
    void registrationFailsIfUsernameAlreadyExists() throws Exception {
        userRepository.save(new User("dupeuser", passwordEncoder.encode("irrelevant")));

        mockMvc.perform(post("/auth/register")
                        .param("username", "dupeuser")
                        .param("password", "newPassword123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("registerUserRequest", "username"))
                .andExpect(view().name("/auth/register"));

        var userCount = userRepository.count();

        assertThat(userCount).isEqualTo(1);
    }

    private Condition<User> matchesPassword(String password) {
        return new Condition<>(user -> passwordEncoder.matches(password, user.getPassword()), "matches password <%s>", password);
    }
}
