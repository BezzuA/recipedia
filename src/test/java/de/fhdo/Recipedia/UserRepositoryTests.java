package de.fhdo.Recipedia;

import de.fhdo.Recipedia.model.User;
import de.fhdo.Recipedia.model.Recipe;
import de.fhdo.Recipedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import de.fhdo.Recipedia.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void testCreateAndFindUser() {
        // Create a new user instance
        User user = new User();
        user.setUserId("2");
        user.setUsername("jane_doe");
        user.setEmail("jane@example.com");
        user.setPassword("securepassword");
        user.setBio("Home cook");

        // Save the user to the repository
        userRepository.save(user);

        // Retrieve the user by ID
        User foundUser = userRepository.findById("2").orElse(null);

        // Assert that the user was found and the username matches
        assert foundUser != null;
        assert foundUser.getUsername().equals("jane_doe");
    }
    
    @Test
    @Transactional
    public void testUserRecipesRelationship() {
        // Create and save a User
        User user = new User();
        user.setUserId("7");
        user.setUsername("recipe_creator");
        user.setEmail("creator@example.com");
        user.setPassword("creatorpass");
        user.setBio("Creates amazing recipes.");
        userRepository.save(user);

        // Create Recipes
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId("4");
        recipe1.setTitle("Veggie Stir Fry");
        recipe1.setDescription("Healthy and quick.");
        recipe1.setInstructions("Stir fry veggies.");
        recipe1.setCreationDate(new Date());

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId("5");
        recipe2.setTitle("Fruit Smoothie");
        recipe2.setDescription("Refreshing drink.");
        recipe2.setInstructions("Blend fruits.");
        recipe2.setCreationDate(new Date());

        // Establish relationships
        user.addRecipe(recipe1);
        user.addRecipe(recipe2);

        // Save the user and recipes
        userRepository.save(user);

        // Retrieve the User
        User foundUser = userRepository.findById("7").orElse(null);

        // Assertions
        assert foundUser != null;
        assert foundUser.getRecipes() != null;
        assert foundUser.getRecipes().size() == 2;
    }

    @Test
    public void testCascadeDeleteUser() {
        // Create and save a User
        User user = new User();
        user.setUserId("8");
        user.setUsername("to_delete");
        user.setEmail("delete@example.com");
        user.setPassword("deletepass");
        user.setBio("Temporary user.");

        // Create a Recipe
        Recipe recipe = new Recipe();
        recipe.setRecipeId("6");
        recipe.setTitle("Temporary Recipe");
        recipe.setDescription("Will be deleted.");
        recipe.setInstructions("No instructions.");
        recipe.setCreationDate(new Date());

        // Establish relationship
        user.addRecipe(recipe);

        // Save the user (cascade will save the recipe)
        userRepository.save(user);

        // Delete the User
        userRepository.delete(user);

        // Check if Recipe is deleted
        Recipe foundRecipe = recipeRepository.findById("6").orElse(null);

        // Assertions
        assert foundRecipe == null;
    }
}
