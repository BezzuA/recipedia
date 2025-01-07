package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.resolver.input.ChallengeInput;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.RecipeService;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.util.List;

@Controller
public class ChallengeResolver {
    private final ChallengeService challengeService;
    private final RecipeService recipeService;
    private final UserService userService;

    public ChallengeResolver(ChallengeService challengeService,
                             RecipeService recipeService,
                             UserService userService) {
        this.challengeService = challengeService;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @QueryMapping
    public ChallengeDto challenge(@Argument Long id) {
        return challengeService.getChallenge(id);
    }

    @QueryMapping
    public List<ChallengeDto> challenges() {
        return challengeService.getChallenges();
    }

    @QueryMapping
    public List<ChallengeDto> challengesByUser(@Argument Long userId) {
        return challengeService.getChallengesByUserId(userId);
    }

    @MutationMapping
    public ChallengeDto createChallenge(@Argument ChallengeInput challenge) {
        ChallengeDto challengeDto = convertInputToDto(challenge);
        return challengeService.createChallenge(challengeDto);
    }

    @MutationMapping
    public ChallengeDto updateChallenge(
            @Argument Long challengeId,
            @Argument ChallengeInput challenge) {
        ChallengeDto challengeDto = convertInputToDto(challenge);
        challengeDto.setChallengeId(challengeId);
        return challengeService.updateChallenge(challengeDto);
    }

    @MutationMapping
    public Boolean deleteChallenge(@Argument Long challengeId) {
        return challengeService.deleteChallenge(challengeId);
    }

    @MutationMapping
    public ChallengeDto joinChallenge(
            @Argument Long challengeId,
            @Argument Long recipeId) {
        return challengeService.joinChallenge(challengeId, recipeId);
    }

    @SchemaMapping(typeName = "Challenge")
    public List<RecipeDto> recipes(ChallengeDto challenge) {
        return recipeService.getRecipesByChallenge(challenge.getChallengeId());
    }

    @SchemaMapping(typeName = "Challenge")
    public List<UserDto> users(ChallengeDto challenge) {
        return userService.getUsersByChallenge(challenge.getChallengeId());
    }

    private ChallengeDto convertInputToDto(ChallengeInput input) {
        ChallengeDto dto = new ChallengeDto();
        dto.setTitle(input.getTitle());
        dto.setDescription(input.getDescription());
        dto.setStartDate(Date.valueOf(input.getStartDate()));
        dto.setEndDate(Date.valueOf(input.getEndDate()));
        return dto;
    }
} 