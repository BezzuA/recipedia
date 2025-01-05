package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.service.CommentService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CommentService commentService;

    public RecipeController(RecipeService recipeService,
                            CommentService commentService) {
        this.recipeService = recipeService;
        this.commentService = commentService;
    }

    @GetMapping("/trendy_recipes")
    public String getTrendyRecipes(Model model) {
        List<RecipeDto> recipes = recipeService.getMostViewedRecipes();

        model.addAttribute("recipes", recipes);

        return "trendy_recipes";
    }

    @GetMapping("/recipe/{id}")
    public String getRecipeDetails(Model model, @PathVariable Long id) {
        RecipeDto recipe = recipeService.getRecipe(id, 1L);
        List<CommentDto> comments = commentService.getCommentsByRecipe(id);

        model.addAttribute("recipe", recipe);
        model.addAttribute("comments", comments);

        return "recipe_details";
    }

}
