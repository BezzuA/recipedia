package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.RatingConverter;
import de.fhdo.Recipedia.dto.RatingDto;
import de.fhdo.Recipedia.entity.Rating;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.RatingRepository;
import de.fhdo.Recipedia.repository.RecipeRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RatingConverter ratingConverter;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             RecipeRepository recipeRepository,
                             UserRepository userRepository,
                             RatingConverter ratingConverter) {
        this.ratingRepository = ratingRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.ratingConverter = ratingConverter;
    }

    @Override
    @Transactional
    public RatingDto addRating(RatingDto ratingDto) {
        Recipe recipe = recipeRepository.findById(ratingDto.getRecipeId()).orElse(null);
        User user = userRepository.findById(ratingDto.getUserId()).orElse(null);

        if (recipe == null || user == null) {
            return null;
        }

        if(ratingDto.getScore() < 1 || ratingDto.getScore() > 5) {
            return null;
        }

        Rating rating = ratingConverter.toEntity(ratingDto);
        Rating existingRating = ratingRepository.findByUserAndRecipe(user, recipe);

        if(existingRating != null) {
            return null;
        }

        rating.setRecipe(recipe);
        rating.setUser(user);
        rating = ratingRepository.save(rating);


        return ratingConverter.toDto(rating);
    }

    @Override
    @Transactional
    public Double getAverageRating(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if(recipe == null) {
            return 0.0;
        }

        List<Integer> scores = recipe.getRatings().stream().map(Rating::getScore).toList();

        if(scores.isEmpty()) {
            return 0.0;
        }

        return scores.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }
}
