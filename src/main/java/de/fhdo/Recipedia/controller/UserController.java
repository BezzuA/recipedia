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

    @GetMapping(
            path = "/{userId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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


    @GetMapping(
            path = "/{authorId}/recipes",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<RecipeDto>> getRecipesByAuthor(
            @PathVariable Long authorId) {
        List<RecipeDto> recipes = recipeService.getRecipesByAuthor(authorId);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{userId}/discussions",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<DiscussionDto>> getDiscussionsByUser(@PathVariable Long userId) {
        List<DiscussionDto> discussions = discussionService.getDiscussionsByUser(userId);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @GetMapping(
            path = "/{userId}/challenges",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<ChallengeDto>> getChallengesByUser(@PathVariable Long userId) {
        List<ChallengeDto> challenges = challengeService.getChallengesByUserId(userId);
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }
}
