package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.UserDto;
import de.fhdo.Recipedia.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.dto.ChallengeDto;
import de.fhdo.Recipedia.service.RecipeService;
import de.fhdo.Recipedia.service.CommentService;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.ReplyService;
import de.fhdo.Recipedia.service.ChallengeService;
import java.util.List;

@Controller
public class UserResolver {
    private final UserService userService;
    private final RecipeService recipeService;
    private final CommentService commentService;
    private final DiscussionService discussionService;
    private final ReplyService replyService;
    private final ChallengeService challengeService;

    public UserResolver(UserService userService,
                       RecipeService recipeService,
                       CommentService commentService,
                       DiscussionService discussionService,
                       ReplyService replyService,
                       ChallengeService challengeService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.commentService = commentService;
        this.discussionService = discussionService;
        this.replyService = replyService;
        this.challengeService = challengeService;
    }

    @QueryMapping
    public UserDto user(@Argument Long id) {
        return userService.getUser(id);
    }

    @MutationMapping
    public UserDto updateUser(
            @Argument Long userId,
            @Argument String username,
            @Argument String email,
            @Argument String bio) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setBio(bio);
        return userService.updateUser(userDto);
    }

    @MutationMapping
    public Boolean changePassword(
            @Argument Long userId,
            @Argument String oldPassword,
            @Argument String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long userId) {
        return userService.deleteUser(userId);
    }

    @SchemaMapping(typeName = "User")
    public List<RecipeDto> recipes(UserDto user) {
        return recipeService.getRecipesByAuthor(user.getUserId());
    }

    @SchemaMapping(typeName = "User")
    public List<CommentDto> comments(UserDto user) {
        return commentService.getCommentsByUser(user.getUserId());
    }

    @SchemaMapping(typeName = "User")
    public List<DiscussionDto> discussions(UserDto user) {
        return discussionService.getDiscussionsByUser(user.getUserId());
    }

    @SchemaMapping(typeName = "User")
    public List<ReplyDto> replies(UserDto user) {
        return replyService.getRepliesByUser(user.getUserId());
    }

    @SchemaMapping(typeName = "User")
    public List<ChallengeDto> challenges(UserDto user) {
        return challengeService.getChallengesByUserId(user.getUserId());
    }
} 