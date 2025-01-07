package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.RecipeConverter;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.entity.Challenge;
import de.fhdo.Recipedia.entity.Rating;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.ChallengeRepository;
import de.fhdo.Recipedia.repository.RatingRepository;
import de.fhdo.Recipedia.repository.RecipeRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.RatingService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final RatingRepository ratingRepository;
    private final RecipeConverter recipeConverter;

    private final RatingService ratingService;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             UserRepository userRepository,
                             ChallengeRepository challengeRepository,
                             RatingRepository ratingRepository,
                             RecipeConverter recipeConverter,
                             RatingService ratingService) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.ratingRepository = ratingRepository;
        this.recipeConverter = recipeConverter;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        User author = userRepository.findById(recipeDto.getAuthor().getUserId()).orElse(null);

        if (author == null) {
            return null;
        }

        Recipe recipe = recipeConverter.toEntity(recipeDto);
        recipe.setAuthor(author);
        recipe.setCreationDate(new Date());

        recipeRepository.save(recipe);


        return recipeConverter.toDto(recipe);
    }

    @Override
    @Transactional
    public Boolean deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            return false;
        }

        recipeRepository.delete(recipe);

        return true;
    }

    @Override
    @Transactional
    public RecipeDto updateRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeRepository.findById(recipeDto.getRecipeId()).orElse(null);

        if (recipe == null) {
            return null;
        }

        recipe.setTitle(recipeDto.getTitle());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setCategory(recipeDto.getCategory());
        recipe.setIngredients(recipeDto.getIngredients());
        recipe.setInstructions(recipeDto.getInstructions());

        recipeRepository.save(recipe);


        return recipeConverter.toDto(recipe);
    }

    @Override
    @Transactional
    public RecipeDto getRecipe(Long recipeId, Long userId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (recipe == null) {
            return null;
        }

        recipe.setViewCount(recipe.getViewCount() + 1);
        recipeRepository.save(recipe);

        RecipeDto recipeDto = recipeConverter.toDto(recipe);

        if (userId == null) {
            recipeDto.setIsRated(false);
            recipeDto.setAverageRating(ratingService.getAverageRating(recipeId));

            return recipeDto;
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            Rating rating = ratingRepository.findByUserAndRecipe(user, recipe);

            if (rating != null) {
                recipeDto.setIsRated(true);
            } else {
                recipeDto.setIsRated(false);
            }
        } else {
            recipeDto.setIsRated(false);
        }

        recipeDto.setAverageRating(ratingService.getAverageRating(recipeId));

        return recipeDto;
    }

    @Override
    @Transactional
    public List<RecipeDto> getRecipes(String keyword, String category) {
        List<Recipe> recipes = new ArrayList<>();

        if(keyword == null || keyword.isEmpty()) {
            if(category == null || category.isEmpty()) {
                recipes = recipeRepository.findAll();
            } else {
                recipes = recipeRepository.findByCategoryOrderByCreationDateDesc(category);
            }
        }else {
            if(category == null || category.isEmpty()) {
                recipes = recipeRepository.findByTitleContainingIgnoreCase(keyword);
            } else {
                recipes = recipeRepository.findByTitleContainingIgnoreCaseAndCategoryOrderByCreationDateDesc(keyword, category);
            }
        }

        return addAverageRatingToRecipeDtos(recipes);
    }

    @Override
    @Transactional
    public List<RecipeDto> getRecipesByAuthor(Long authorId) {
        User author = userRepository.findById(authorId).orElse(null);

        if (author == null) {
            return null;
        }

        List<Recipe> recipes = recipeRepository.findByAuthorOrderByCreationDateDesc(author);


        return addAverageRatingToRecipeDtos(recipes);
    }

    @Override
    @Transactional
    public List<RecipeDto> getRecipesByChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);

        if (challenge == null) {
            return null;
        }

        List<Recipe> recipes = challenge.getRecipes();


        return addAverageRatingToRecipeDtos(recipes);
    }

    @Override
    @Transactional
    public List<RecipeDto> getMostViewedRecipes() {
        List<Recipe> recipes = recipeRepository.findAllByOrderByViewCountDesc();

        return addAverageRatingToRecipeDtos(recipes);
    }

    @Override
    public List<RecipeDto> getTrendyRecipes() {
        return List.of();
    }


    private List<RecipeDto> addAverageRatingToRecipeDtos(List<Recipe> recipes) {
        List<RecipeDto> recipeDtos = recipes.stream().map(recipeConverter::toDto).toList();

        for (RecipeDto recipeDto : recipeDtos) {
            recipeDto.setAverageRating(ratingService.getAverageRating(recipeDto.getRecipeId()));
        }

        return recipeDtos;
    }
}
