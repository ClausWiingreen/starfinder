package dk.wiingreen.starfinder;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping
    String getCharacterOverview(Model model) {
        return currentUserService.getCurrentUser().map(user -> {
            model.addAttribute("characters", characterRepository.findByOwner(user));
            return "/characters/overview";
        }).orElse("redirect:/login");
    }

    @PostMapping
    String addCharacter(@Valid @ModelAttribute CharacterCreateRequest request,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/characters/form";
        }

        return currentUserService.getCurrentUser().map(user -> {
            var character = user.createCharacter(request.name());
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
    String updateCharacter(@PathVariable UUID id,
                           @Valid @ModelAttribute CharacterEditRequest request,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "/characters/edit";
        }

        return currentUserService.getCurrentUser().map(user ->
                characterRepository.findByIdAndOwner(id, user)
                        .map(character -> {
                            character.setName(request.name());
                            characterRepository.save(character);
                            return "redirect:/characters";
                        }).orElseGet(() -> {
                            model.addAttribute(
                                    "error",
                                    "Failed to find character with id %s".formatted(id));
                            return "/error/not-found";
                        })).orElse("redirect:/login");
    }

    @PostMapping("/{id}/delete")
    String deleteCharacter(@PathVariable UUID id) {
        characterRepository.deleteById(id);
        return "redirect:/characters";
    }
}
