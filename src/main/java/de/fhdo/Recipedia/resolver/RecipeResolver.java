package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.dto.RatingDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.resolver.input.RecipeInput;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.CommentService;
import de.fhdo.Recipedia.service.RatingService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RecipeResolver {
    private final RecipeService recipeService;
    private final ChallengeService challengeService;
    private final CommentService commentService;
    private final RatingService ratingService;


    public RecipeResolver(RecipeService recipeService,
                          ChallengeService challengeService,
                          CommentService commentService,
                          RatingService ratingService) {
        this.recipeService = recipeService;
        this.challengeService = challengeService;
        this.commentService = commentService;
        this.ratingService = ratingService;
    }

    @QueryMapping
    public RecipeDto recipe(@Argument Long id, @Argument(name = "userId") Long userId) {
        return recipeService.getRecipe(id, userId);
    }

    @QueryMapping
    public List<RecipeDto> recipes(
            @Argument String keyword,
            @Argument String category) {
        return recipeService.getRecipes(keyword, category);
    }

    @QueryMapping
    public List<RecipeDto> popularRecipes() {
        return recipeService.getMostViewedRecipes();
    }

    @QueryMapping
    public List<RecipeDto> recipesByAuthor(@Argument Long authorId) {
        return recipeService.getRecipesByAuthor(authorId);
    }

    @QueryMapping
    public List<RecipeDto> recipesByChallenge(@Argument Long challengeId) {
        return recipeService.getRecipesByChallenge(challengeId);
    }

    @MutationMapping
    public RecipeDto createRecipe(@Argument RecipeInput recipe) {
        RecipeDto recipeDto = convertInputToDto(recipe);
        return recipeService.createRecipe(recipeDto);
    }

    @MutationMapping
    public RecipeDto updateRecipe(
            @Argument Long recipeId,
            @Argument RecipeDto recipe) {
        recipe.setRecipeId(recipeId);
        return recipeService.updateRecipe(recipe);
    }

    @MutationMapping
    public Boolean deleteRecipe(@Argument Long recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }

    @SchemaMapping(typeName = "Recipe")
    public ChallengeDto challenge(RecipeDto recipe) {
        return challengeService.getChallengeByRecipe(recipe.getRecipeId());
    }

    @SchemaMapping(typeName = "Recipe")
    public List<CommentDto> comments(RecipeDto recipe) {
        return commentService.getCommentsByRecipe(recipe.getRecipeId());
    }

    @SchemaMapping(typeName = "Recipe")
    public List<RatingDto> ratings(RecipeDto recipe) {
        return ratingService.getRatingsByRecipe(recipe.getRecipeId());
    }


    private RecipeDto convertInputToDto(RecipeInput input) {
        RecipeDto dto = new RecipeDto();
        dto.setTitle(input.getTitle());
        dto.setDescription(input.getDescription());
        dto.setCategory(input.getCategory());
        dto.setIngredients(input.getIngredients());
        dto.setInstructions(input.getInstructions());
        dto.getAuthor().setUserId(input.getAuthorId());
        return dto;
    }

} 