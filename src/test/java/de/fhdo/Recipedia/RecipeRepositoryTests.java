package de.fhdo.Recipedia;

import de.fhdo.Recipedia.model.*;
import de.fhdo.Recipedia.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class RecipeRepositoryTests {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    public void testCreateAndFindRecipe() {
        // Create and save a User
        User author = new User();
        author.setUserId("2");
        author.setUsername("chef_john");
        author.setEmail("john@example.com");
        author.setPassword("securepassword");
        author.setBio("Professional chef");
        userRepository.save(author);

        // Create and save a Challenge
        Challenge challenge = new Challenge();
        challenge.setChallengeId("1");
        challenge.setTitle("Summer Salads");
        challenge.setDescription("Create a refreshing salad for summer.");
        challenge.setStartDate(new Date());
        challenge.setEndDate(new Date(System.currentTimeMillis() + 86400000L)); // +1 day
        challengeRepository.save(challenge);

        // Create and save a Recipe
        Recipe recipe = new Recipe();
        recipe.setRecipeId("1");
        recipe.setTitle("Quinoa Salad");
        recipe.setDescription("A healthy quinoa salad with veggies.");
        recipe.setInstructions("Cook quinoa. Chop veggies. Mix together.");
        recipe.setAuthor(author);
        recipe.setChallenge(challenge);
        recipe.setCreationDate(new Date());
        recipeRepository.save(recipe);

        // Retrieve the Recipe
        Recipe foundRecipe = recipeRepository.findById("1").orElse(null);

        // Assertions
        assert foundRecipe != null;
        assert foundRecipe.getTitle().equals("Quinoa Salad");
        assert foundRecipe.getAuthor().getUsername().equals("chef_john");
        assert foundRecipe.getChallenge().getTitle().equals("Summer Salads");
    }
}
