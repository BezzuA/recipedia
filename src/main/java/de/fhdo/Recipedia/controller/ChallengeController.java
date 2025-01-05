package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChallengeController {
    private final ChallengeService challengeService;
    private final RecipeService recipeService;

    public ChallengeController(ChallengeService challengeService,
                               RecipeService recipeService) {
        this.challengeService = challengeService;
        this.recipeService = recipeService;
    }

    @GetMapping("/challenges")
    public String getChallenges(Model model) {
        List<ChallengeDto> challenges = challengeService.getChallenges();

        model.addAttribute("challenges", challenges);

        return "challenges";
    }

    @GetMapping("/challenge/{id}")
    public String getChallengeDetails(Model model, @PathVariable Long id) {
        ChallengeDto challenge = challengeService.getChallenge(id);
        List<RecipeDto> recipes = recipeService.getRecipesByChallenge(id);

        model.addAttribute("challenge", challenge);
        model.addAttribute("recipes", recipes);

        return "challenge_details";
    }
}
