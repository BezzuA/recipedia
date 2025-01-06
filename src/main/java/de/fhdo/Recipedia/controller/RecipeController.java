package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeDto createdRecipe = recipeService.createRecipe(recipeDto);
        if (createdRecipe != null) {
            return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(
        path = "/{recipeId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<RecipeDto> getRecipe(
            @PathVariable Long recipeId,
            @RequestParam(required = false) Long userId) {
        RecipeDto recipe = recipeService.getRecipe(recipeId, userId);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(
        path = "/{recipeId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<RecipeDto> updateRecipe(
            @PathVariable Long recipeId,
            @RequestBody RecipeDto recipeDto) {
        recipeDto.setRecipeId(recipeId);
        RecipeDto updatedRecipe = recipeService.updateRecipe(recipeDto);
        if (updatedRecipe != null) {
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId) {
        Boolean success = recipeService.deleteRecipe(recipeId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(
        path = "/search",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> searchRecipes(
            @RequestParam(required = false) String keyword) {
        List<RecipeDto> recipes = recipeService.getRecipes(keyword);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(
        path = "/category/{category}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByCategory(
            @PathVariable String category) {
        List<RecipeDto> recipes = recipeService.getRecipesByCategory(category);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(
        path = "/author/{authorId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByAuthor(
            @PathVariable Long authorId) {
        List<RecipeDto> recipes = recipeService.getRecipesByAuthor(authorId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(
        path = "/challenge/{challengeId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByChallenge(
            @PathVariable Long challengeId) {
        List<RecipeDto> recipes = recipeService.getRecipesByChallenge(challengeId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(
        path = "/popular",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getMostViewedRecipes() {
        List<RecipeDto> recipes = recipeService.getMostViewedRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
