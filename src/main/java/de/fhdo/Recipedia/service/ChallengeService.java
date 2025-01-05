package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.entity.Recipe;

import java.util.List;

public interface ChallengeService {
    ChallengeDto addChallenge(ChallengeDto challengeDto);
    Boolean deleteChallenge(Long challengeId);
    ChallengeDto updateChallenge(ChallengeDto challengeDto);
    ChallengeDto getChallenge(Long challengeId);
    List<ChallengeDto> getChallenges();
    ChallengeDto joinChallenge(Long challengeId, Long recipeId);
    List<Recipe> getWinnerRecipesByChallengeId(Long challengeId);
}
