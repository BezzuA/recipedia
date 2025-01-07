package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.RecipeDto;

import java.util.List;

public interface ChallengeService {
    ChallengeDto createChallenge(ChallengeDto challengeDto);
    Boolean deleteChallenge(Long challengeId);
    ChallengeDto updateChallenge(ChallengeDto challengeDto);
    ChallengeDto getChallenge(Long challengeId);
    ChallengeDto joinChallenge(Long challengeId, Long recipeId);
    List<ChallengeDto> getChallengesByUserId(Long userId);
    List<ChallengeDto> getChallenges();
    List<RecipeDto> getWinnerRecipesByChallengeId(Long challengeId);
    ChallengeDto getChallengeByRecipe(Long recipeId);
}
