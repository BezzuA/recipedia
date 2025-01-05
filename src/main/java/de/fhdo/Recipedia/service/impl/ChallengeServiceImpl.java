package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.ChallengeConverter;
import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.entity.Challenge;
import de.fhdo.Recipedia.entity.Comment;
import de.fhdo.Recipedia.entity.Rating;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.repository.ChallengeRepository;
import de.fhdo.Recipedia.repository.RecipeRepository;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.RatingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final RecipeRepository recipeRepository;
    private final ChallengeConverter challengeConverter;

    private final RatingService ratingService;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository,
                                RecipeRepository recipeRepository,
                                ChallengeConverter challengeConverter,
                                RatingService ratingService) {
        this.challengeRepository = challengeRepository;
        this.recipeRepository = recipeRepository;
        this.challengeConverter = challengeConverter;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public ChallengeDto addChallenge(ChallengeDto challengeDto) {
        Date startDate = challengeDto.getStartDate();
        Date endDate = challengeDto.getEndDate();

        if (startDate.after(endDate)) {
            return null;
        }

        Challenge challenge = challengeConverter.toEntity(challengeDto);

        challengeRepository.save(challenge);

        return challengeConverter.toDto(challenge);
    }

    @Override
    @Transactional
    public Boolean deleteChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);

        if (challenge == null) {
            return false;
        }

        for (Recipe recipe : challenge.getRecipes()) {
            recipe.setChallenge(null);
            recipeRepository.save(recipe);
        }

        challenge.getRecipes().clear();

        challengeRepository.delete(challenge);

        return true;
    }

    @Override
    @Transactional
    public ChallengeDto updateChallenge(ChallengeDto challengeDto) {
        Challenge challenge = challengeRepository.findById(challengeDto.getChallengeId()).orElse(null);

        if (challenge == null) {
            return null;
        }

        challenge.setTitle(challengeDto.getTitle());
        challenge.setDescription(challengeDto.getDescription());
        challenge.setStartDate(challengeDto.getStartDate());
        challenge.setEndDate(challengeDto.getEndDate());

        challengeRepository.save(challenge);

        return challengeConverter.toDto(challenge);
    }

    @Override
    @Transactional
    public ChallengeDto getChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);

        if (challenge == null) {
            return null;
        }

        return challengeConverter.toDto(challenge);
    }

    @Override
    @Transactional
    public List<ChallengeDto> getChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();

        return challenges.stream().map(challengeConverter::toDto).toList();
    }

    @Override
    @Transactional
    public ChallengeDto joinChallenge(Long challengeId, Long recipeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if (challenge == null || recipe == null) {
            return null;
        }

        challenge.getRecipes().add(recipe);
        recipe.setChallenge(challenge);

        challengeRepository.save(challenge);
        recipeRepository.save(recipe);


        return challengeConverter.toDto(challenge);
    }

    @Override
    @Transactional
    public List<Recipe> getWinnerRecipesByChallengeId(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);

        if (challenge == null) {
            return null;
        }

        List<Recipe> recipes = challenge.getRecipes();

        if (recipes.isEmpty()) {
            return null;
        }

        List<Double> averageRatings = recipes.stream().map(recipe -> ratingService.getAverageRating(recipe.getRecipeId())).toList();
        List<Integer> commentsCount = recipes.stream().map(recipe -> recipe.getComments().size()).toList();
        List<Long> viewCount = recipes.stream().map(Recipe::getViewCount).toList();

        // average rating * 0.4 + comments count * 0.4 + view count * 0.2
        List<Double> scores = new ArrayList<>();

        for(int i = 0; i < recipes.size(); i++) {
            scores.add(averageRatings.get(i) * 0.4 + commentsCount.get(i) * 0.4 + viewCount.get(i) * 0.2);
        }

        double maxScore = scores.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);

        List<Recipe> winnerRecipes = new ArrayList<>();
        for(int i = 0; i < recipes.size(); i++) {
            if(scores.get(i) == maxScore) {
                winnerRecipes.add(recipes.get(i));
            }
        }

        return winnerRecipes;
    }
}
