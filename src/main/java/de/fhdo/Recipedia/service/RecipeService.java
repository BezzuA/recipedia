package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.model.Recipe;
import de.fhdo.Recipedia.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(String recipeId, Recipe updatedRecipe) {
        return recipeRepository.findById(recipeId)
                .map(recipe -> {
                    recipe.setTitle(updatedRecipe.getTitle());
                    recipe.setDescription(updatedRecipe.getDescription());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setInstructions(updatedRecipe.getInstructions());
                    return recipeRepository.save(recipe);
                }).orElseThrow(() -> new RuntimeException("Recipe not found!"));
    }

    public void deleteRecipe(String recipeId) {
        recipeRepository.deleteById(recipeId);
    }
    
    // Implement search logic
    public List<Recipe> searchRecipes(String keyword) {
        return recipeRepository.findAll();
    }

    // Implement filter logic
    public List<Recipe> filterRecipesByPreferences(String type, List<String> preferences) {
        return recipeRepository.findAll();
    }
}
