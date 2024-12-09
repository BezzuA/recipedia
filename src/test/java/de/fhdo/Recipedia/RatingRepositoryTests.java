package de.fhdo.Recipedia;

import de.fhdo.Recipedia.model.*;
import de.fhdo.Recipedia.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class RatingRepositoryTests {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void testCreateAndFindRating() {
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
    }
}
