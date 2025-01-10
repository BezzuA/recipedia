package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.dto.RecipeDto;
import org.springframework.stereotype.Component;

@Component
public class RecipeConverter {

    public RecipeDto toDto(Recipe recipe) {
        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setRecipeId(recipe.getRecipeId());
        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setCategory(recipe.getCategory());
        recipeDto.setViewCount(recipe.getViewCount());
        recipeDto.setIngredients(recipe.getIngredients());
        recipeDto.setInstructions(recipe.getInstructions());
        recipeDto.setCreationDate(recipe.getCreationDate());
        recipeDto.setChallenge(recipe.getChallenge() != null ? new ChallengeConverter().toDto(recipe.getChallenge()) : null);

        recipeDto.getAuthor().setUserId(recipe.getAuthor().getUserId());
        recipeDto.getAuthor().setUsername(recipe.getAuthor().getUsername());

        return recipeDto;
    }

    public Recipe toEntity(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();

        recipe.setTitle(recipeDto.getTitle());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setCategory(recipeDto.getCategory());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setInstructions(recipeDto.getInstructions());

        return recipe;
    }
}
