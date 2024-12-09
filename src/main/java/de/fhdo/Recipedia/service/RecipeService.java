package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.model.Rating;
import de.fhdo.Recipedia.model.Recipe;
import de.fhdo.Recipedia.model.User;
import de.fhdo.Recipedia.repository.RatingRepository;
import de.fhdo.Recipedia.repository.RecipeRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, RatingRepository ratingRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

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

    public Recipe getRecipeById(String recipeId) {
        return recipeRepository.findById(recipeId).orElse(null);
    }

    public List<Recipe> getRecommendedRecipes() {
        // Create and save a User
        User user = new User();
        user.setUserId("4");
        user.setUsername("critic_jane");
        user.setEmail("jane@example.com");
        user.setPassword("password123");
        user.setBio("Professional food critic.");
        userRepository.save(user);

        // Create and save a Recipe
        Recipe recipe = new Recipe();
        recipe.setRecipeId("3");
        recipe.setTitle("Chocolate Cake");
        recipe.setDescription("Delicious and moist chocolate cake.");
        recipe.setInstructions("Mix ingredients. Bake in oven.");
        recipe.setCreationDate(new Date());
        recipeRepository.save(recipe);

        // Create and save a Rating
        Rating rating = new Rating();
        rating.setRatingId("1");
        rating.setScore(5);
        rating.setDate(new Date());
        rating.setUser(user);
        rating.setRecipe(recipe);
        ratingRepository.save(rating);

        // Retrieve the Rating
        Rating foundRating = ratingRepository.findById("1").orElse(null);

        // Assertions
        assert foundRating != null;
        assert foundRating.getScore() == 5;
        assert foundRating.getUser().getUsername().equals("critic_jane");
        assert foundRating.getRecipe().getTitle().equals("Chocolate Cake");

        return recipeRepository.findAll();
    }
}
