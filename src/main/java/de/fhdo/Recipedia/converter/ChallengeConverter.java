package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.entity.Challenge;
import org.springframework.stereotype.Component;

@Component
public class ChallengeConverter {

    public ChallengeDto toDto(Challenge challenge) {
        ChallengeDto challengeDto = new ChallengeDto();

        challengeDto.setChallengeId(challenge.getChallengeId());
        challengeDto.setTitle(challenge.getTitle());
        challengeDto.setDescription(challenge.getDescription());
        challengeDto.setStartDate(challenge.getStartDate());
        challengeDto.setEndDate(challenge.getEndDate());
        challengeDto.setNumberOfRecipes(challenge.getRecipes().size());

        return challengeDto;
    }

    public Challenge toEntity(ChallengeDto challengeDto) {
        Challenge challenge = new Challenge();

        challenge.setTitle(challengeDto.getTitle());
        challenge.setDescription(challengeDto.getDescription());
        challenge.setStartDate(challengeDto.getStartDate());
        challenge.setEndDate(challengeDto.getEndDate());

        return challenge;
    }
}
