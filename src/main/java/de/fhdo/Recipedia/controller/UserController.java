package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.RecipeService;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RecipeService recipeService;
    private final ChallengeService challengeService;
    private final DiscussionService discussionService;

    public UserController(UserService userService,
                          RecipeService recipeService,
                          ChallengeService challengeService,
                          DiscussionService discussionService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.challengeService = challengeService;
        this.discussionService = discussionService;
    }

    @GetMapping("/account")
    public String getAccount(Model model) {
        UserDto user = userService.login("billy_herrington", "password");
        List<RecipeDto> recipes = recipeService.getRecipesByAuthor(user.getUserId());
        List<ChallengeDto> challenges = challengeService.getChallengesByUserId(user.getUserId());
        List<DiscussionDto> discussions = discussionService.getDiscussionsByUser(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        model.addAttribute("challenges", challenges);
        model.addAttribute("discussions", discussions);

        return "account";
    }
}
