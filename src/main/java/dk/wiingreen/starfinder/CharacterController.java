package dk.wiingreen.starfinder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/characters")
class CharacterController {
    private final CharacterRepository characterRepository;
    private final CurrentUserService currentUserService;

    CharacterController(CharacterRepository characterRepository, CurrentUserService currentUserService) {
        this.characterRepository = characterRepository;
        this.currentUserService = currentUserService;
    }

    @PostMapping
    String addCharacter(@Valid @ModelAttribute CharacterCreateRequest request,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/characters/form";
        }

        return currentUserService.getCurrentUser().map(user -> {
            var character = new Character();
            character.setName(request.name());
            character.setOwner(user);
            characterRepository.save(character);
            return "redirect:/characters";
        }).orElseGet(() -> {
            model.addAttribute(
                    "error",
                    "You must be logged in to create a character.");
            return "/error/unauthorized";
        });
    }

    @PostMapping("/{id}")
    String updateCharacter(@PathVariable UUID id, String name, Model model) {
        return currentUserService.getCurrentUser().map(user ->
                characterRepository.findByIdAndOwner(id, user)
                        .map(character -> {
                            character.setName(name);
                            characterRepository.save(character);
                            return "redirect:/characters";
                        }).orElseGet(() -> {
                            model.addAttribute("error", "Failed to find character with id %s".formatted(id));
                            return "/error/not-found";
                        })).orElse("redirect:/login");
    }
}
