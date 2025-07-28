package dk.wiingreen.starfinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterRepository characterRepository;

    public CharacterController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @PostMapping
    public String addCharacter(String name) {
        var character = new Character();
        character.setName(name);
        characterRepository.save(character);
        return "redirect:/characters";
    }
}
