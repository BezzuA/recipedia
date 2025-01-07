package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.RatingDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.resolver.input.RatingInput;
import de.fhdo.Recipedia.service.RatingService;
import de.fhdo.Recipedia.service.RecipeService;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RatingResolver {
    private final RatingService ratingService;
    private final RecipeService recipeService;
    private final UserService userService;

    public RatingResolver(RatingService ratingService,
                          RecipeService recipeService,
                          UserService userService) {
        this.ratingService = ratingService;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @QueryMapping
    public Double averageRating(@Argument Long recipeId) {
        return ratingService.getAverageRating(recipeId);
    }

    @MutationMapping
    public RatingDto rateRecipe(@Argument RatingInput rating) {
        RatingDto ratingDto = convertInputToDto(rating);
        return ratingService.createRating(ratingDto);
    }

    @SchemaMapping(typeName = "Rating")
    public UserDto user(RatingDto rating) {
        return userService.getUser(rating.getUserId());
    }

    @SchemaMapping(typeName = "Rating")
    public RecipeDto recipe(RatingDto rating) {
        return recipeService.getRecipe(rating.getRecipeId(), null);
    }

    private RatingDto convertInputToDto(RatingInput input) {
        RatingDto dto = new RatingDto();
        dto.setRecipeId(input.getRecipeId());
        dto.setUserId(input.getUserId());
        dto.setScore(input.getScore());
        return dto;
    }
} 