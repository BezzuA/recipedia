package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;
    private final RecipeService recipeService;

    public ChallengeController(ChallengeService challengeService,
                               RecipeService recipeService) {
        this.challengeService = challengeService;
        this.recipeService = recipeService;
    }

    @PostMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ChallengeDto> createChallenge(@RequestBody ChallengeDto challengeDto) {
        ChallengeDto createdChallenge = challengeService.addChallenge(challengeDto);
        if (createdChallenge != null) {
            return new ResponseEntity<>(createdChallenge, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(
        path = "/{challengeId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable Long challengeId) {
        ChallengeDto challenge = challengeService.getChallenge(challengeId);
        if (challenge != null) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(
        path = "/{challengeId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ChallengeDto> updateChallenge(
            @PathVariable Long challengeId,
            @RequestBody ChallengeDto challengeDto) {
        challengeDto.setChallengeId(challengeId);
        ChallengeDto updatedChallenge = challengeService.updateChallenge(challengeDto);
        if (updatedChallenge != null) {
            return new ResponseEntity<>(updatedChallenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{challengeId}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long challengeId) {
        Boolean success = challengeService.deleteChallenge(challengeId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(
            path = "/{challengeId}/recipes",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByChallenge(
            @PathVariable Long challengeId) {
        List<RecipeDto> recipes = recipeService.getRecipesByChallenge(challengeId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping(
        path = "/{challengeId}/join",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ChallengeDto> joinChallenge(
            @PathVariable Long challengeId,
            @RequestParam Long recipeId) {
        ChallengeDto challenge = challengeService.joinChallenge(challengeId, recipeId);
        if (challenge != null) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
        List<ChallengeDto> challenges = challengeService.getChallenges();
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @GetMapping(
        path = "/{challengeId}/winners",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getWinnerRecipes(@PathVariable Long challengeId) {
        List<RecipeDto> winners = challengeService.getWinnerRecipesByChallengeId(challengeId);
        if (!winners.isEmpty()) {
            return new ResponseEntity<>(winners, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
