package de.fhdo.Recipedia;

import de.fhdo.Recipedia.model.*;
import de.fhdo.Recipedia.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
public class CommentRepositoryTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void testCreateAndFindComment() {
        // Create and save a User
        User user = new User();
        user.setUserId("3");
        user.setUsername("foodie123");
        user.setEmail("foodie@example.com");
        user.setPassword("mypassword");
        user.setBio("Loves trying new recipes.");
        userRepository.save(user);

        // Create and save a Recipe
        Recipe recipe = new Recipe();
        recipe.setRecipeId("2");
        recipe.setTitle("Pasta Carbonara");
        recipe.setDescription("Classic Italian pasta.");
        recipe.setInstructions("Cook pasta. Prepare sauce. Mix together.");
        recipe.setCreationDate(new Date());
        recipeRepository.save(recipe);

        // Create and save a Comment
        Comment comment = new Comment();
        comment.setCommentId("1");
        comment.setText("This recipe is amazing!");
        comment.setCreationDate(new Date());
        comment.setUser(user);
        comment.setRecipe(recipe);
        commentRepository.save(comment);

        // Retrieve the Comment
        Comment foundComment = commentRepository.findById("1").orElse(null);

        // Assertions
        assert foundComment != null;
        assert foundComment.getText().equals("This recipe is amazing!");
        assert foundComment.getUser().getUsername().equals("foodie123");
        assert foundComment.getRecipe().getTitle().equals("Pasta Carbonara");
    }
}
