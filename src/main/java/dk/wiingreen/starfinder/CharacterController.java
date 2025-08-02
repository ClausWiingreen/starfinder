package dk.wiingreen.starfinder;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Controller
@RequestMapping("/characters")
class CharacterController {
    private static final Logger log = LoggerFactory.getLogger(CharacterController.class);
    private final CharacterRepository characterRepository;
    private final CurrentUserService currentUserService;

    CharacterController(CharacterRepository characterRepository, CurrentUserService currentUserService) {
        this.characterRepository = characterRepository;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    String getCharacterOverview(Model model) {
        return withUser(user -> {
            model.addAttribute("characters", characterRepository.findByOwner(user));
            model.addAttribute("characterCreateRequest", new CharacterCreateRequest(""));
            return "/characters/overview";
        });
    }

    @GetMapping("/new")
    String getCreateCharacterForm(Model model) {
        model.addAttribute("characterCreateRequest", new CharacterCreateRequest(""));
        return "/characters/new";
    }

    @PostMapping("/new")
    String createCharacter(@Valid @ModelAttribute CharacterCreateRequest request,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/characters/new";
        }

        return withUser(user -> {
            var character = user.createCharacter(request.name().trim());
            characterRepository.save(character);
            return "redirect:/characters";
        });
    }


    @GetMapping("/{id}")
    String getCharacter(@PathVariable UUID id, Model model) {
        return withCharacter(id, model, character -> {
            model.addAttribute("characterEditRequest", new CharacterEditRequest(character.getName()));
            return "characters/edit";
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

        return withCharacter(id, model, character -> {
            var newName = request.name().trim();
            if (!Objects.equals(newName, character.getName())) {
                log.info("Updating character <{}> with name '{}'", id, newName);
                character.setName(newName);
            }
            characterRepository.save(character);
            return "redirect:/characters";
        });
    }

    @PostMapping("/{id}/delete")
    String deleteCharacter(@PathVariable UUID id) {
        return withUser(user -> {
            characterRepository.deleteByIdAndOwner(id, user);
            return "redirect:/characters";
        });
    }

    private String withUser(Function<User, String> action) {
        return currentUserService.getCurrentUser().map(action).orElse("redirect:/login");
    }

    private String withCharacter(UUID id, Model model, Function<Character, String> action) {
        return withUser(user -> characterRepository.findByIdAndOwner(id, user)
                .map(action)
                .orElseGet(() -> {
                    model.addAttribute("status", 404);
                    model.addAttribute("error", "Character not found");
                    model.addAttribute("message", "Failed to find character with id %s".formatted(id));
                    return "/error";
                }));
    }
}
