package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.model.Recipe;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{recipeId}")
    public String recipe(Model model, @PathVariable String recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        model.addAttribute("recipe", recipe);

        return "recipe";
    }
}
