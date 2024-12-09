package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.model.Recipe;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final RecipeService recipeService;

    HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recommendedRecipes = recipeService.getRecommendedRecipes();
        model.addAttribute("recommendedRecipes", recommendedRecipes);

        return "index";
    }
}
