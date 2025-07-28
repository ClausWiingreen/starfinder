package dk.wiingreen.starfinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/characters")
public class CharacterController {
    @PostMapping
    public String addCharacter() {
        return "redirect:/characters";
    }
}
