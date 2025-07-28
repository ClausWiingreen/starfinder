package dk.wiingreen.starfinder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CharacterIntegrationTests {
    private final UserRepository userRepository;
    private final MockMvc mockMvc;
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterIntegrationTests(UserRepository userRepository, MockMvc mockMvc, CharacterRepository characterRepository) {
        this.userRepository = userRepository;
        this.mockMvc = mockMvc;
        this.characterRepository = characterRepository;
    }

    @Test
    void characterIsSavedForLoggedInUserWhenFormIsSubmitted() throws Exception {
        var user = new User();
        user.setUsername("testuser");
        userRepository.save(user);

        mockMvc.perform(post("/characters")
                        .param("name", "Nova Vance"))
                .andExpect(status().is3xxRedirection());

        var characters = characterRepository.findAll();

        assertThat(characters).hasSize(1);
    }
}
