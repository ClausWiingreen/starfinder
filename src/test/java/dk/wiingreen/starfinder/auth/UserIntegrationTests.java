package dk.wiingreen.starfinder.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserIntegrationTests {
  private final UserRepository userRepository;
  private final MockMvc mockMvc;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserIntegrationTests(
      UserRepository userRepository, MockMvc mockMvc, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.mockMvc = mockMvc;
    this.passwordEncoder = passwordEncoder;
  }

  @Test
  void newUserCanRegisterAndLoginSuccessfully() throws Exception {
    mockMvc
        .perform(
            post("/auth/register")
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
    createUser("dupeuser", "irrelevant");

    mockMvc
        .perform(
            post("/auth/register")
                .param("username", "dupeuser")
                .param("password", "newPassword123")
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("registerUserRequest", "username"))
        .andExpect(view().name("/auth/register"));

    var userCount = userRepository.count();

    assertThat(userCount).isEqualTo(1);
  }

  @Test
  void loginFailsWithWrongPassword() throws Exception {
    createUser("loginuser", "corretPassword");

    mockMvc
        .perform(
            post("/login")
                .param("username", "loginuser")
                .param("password", "wrongPassword")
                .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login?error"));
  }

  @Test
  void registrationFailsWithBlankPassword() throws Exception {
    mockMvc
        .perform(
            post("/auth/register")
                .param("username", "newuser")
                .param("password", "") // Blank password
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("registerUserRequest", "password"))
        .andExpect(view().name("/auth/register"));

    assertThat(userRepository.findByUsername("newuser")).isEmpty();
  }

  @Test
  void loginFailsForUnknownUser() throws Exception {
    mockMvc
        .perform(
            post("/login")
                .param("username", "ghostuser")
                .param("password", "anyPassword")
                .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login?error"));
  }

  @Test
  void registrationFailsWithBlankUsername() throws Exception {
    mockMvc
        .perform(
            post("/auth/register")
                .param("username", "")
                .param("password", "validPassword123")
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(model().attributeHasFieldErrors("registerUserRequest", "username"))
        .andExpect(view().name("/auth/register"));

    assertThat(userRepository.findAll()).isEmpty();
  }

  @Test
  @WithMockUser("someone")
  void logoutClearsSessionAndRedirects() throws Exception {
    var result =
        mockMvc
            .perform(post("/logout").with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login?logout"))
            .andReturn();

    var session = result.getRequest().getSession(false);
    assertThat(session).isNull();
  }

  private void createUser(String username, String password) {
    userRepository.save(new User(username, passwordEncoder.encode(password)));
  }

  private Condition<User> matchesPassword(String password) {
    return new Condition<>(
        user -> passwordEncoder.matches(password, user.getPassword()),
        "matches password <%s>",
        password);
  }
}
