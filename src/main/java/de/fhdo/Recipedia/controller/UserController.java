package de.fhdo.Recipedia.controller;

import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.service.ChallengeService;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.RecipeService;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RecipeService recipeService;
    private final DiscussionService discussionService;
    private final ChallengeService challengeService;

    public UserController(UserService userService,
                          RecipeService recipeService,
                          DiscussionService discussionService,
                          ChallengeService challengeService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.discussionService = discussionService;
        this.challengeService = challengeService;
    }

    @PatchMapping(
        path = "/{userId}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserDto userDto) {
        userDto.setUserId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        Boolean success = userService.deleteUser(userId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        // Example: Hardcode user ID 1, or get from security context
        Long userId = 1L;
        UserDto userDto = userService.getUserById(userId);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }



    @GetMapping(
            path = "/{authorId}/recipes",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByAuthor(
            @PathVariable Long authorId) {
        List<RecipeDto> recipes = recipeService.getRecipesByAuthor(authorId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/me/recipes")
    public ResponseEntity<List<RecipeDto>> getRecipesForCurrentUser() {
        Long userId = 1L; // or from security
        List<RecipeDto> recipes = recipeService.getRecipesByAuthor(userId);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping(
            path = "/{userId}/discussions",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<DiscussionDto>> getDiscussionsByUser(@PathVariable Long userId) {
        List<DiscussionDto> discussions = discussionService.getDiscussionsByUser(userId);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @GetMapping("/me/discussions")
    public ResponseEntity<List<DiscussionDto>> getDiscussionsForCurrentUser() {
        Long userId = 1L;
        List<DiscussionDto> discussions = discussionService.getDiscussionsByUser(userId);
        return ResponseEntity.ok(discussions);
    }

    @GetMapping(
            path = "/{userId}/challenges",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<ChallengeDto>> getChallengesByUser(@PathVariable Long userId) {
        List<ChallengeDto> challenges = challengeService.getChallengesByUserId(userId);
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }

    @GetMapping("/me/challenges")
    public ResponseEntity<List<ChallengeDto>> getChallengesForCurrentUser() {
        Long userId = 1L;
        List<ChallengeDto> challenges = challengeService.getChallengesByUserId(userId);
        return ResponseEntity.ok(challenges);
    }
}
