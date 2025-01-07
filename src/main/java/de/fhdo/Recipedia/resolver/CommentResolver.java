package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.dto.RecipeDto;
import de.fhdo.Recipedia.resolver.input.CommentInput;
import de.fhdo.Recipedia.service.CommentService;
import de.fhdo.Recipedia.service.RecipeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommentResolver {
    private final CommentService commentService;
    private final RecipeService recipeService;

    public CommentResolver(CommentService commentService, RecipeService recipeService) {
        this.commentService = commentService;
        this.recipeService = recipeService;
    }

    @QueryMapping
    public List<CommentDto> commentsByRecipe(@Argument Long recipeId) {
        return commentService.getCommentsByRecipe(recipeId);
    }

    @MutationMapping
    public CommentDto createComment(@Argument CommentInput comment) {
        CommentDto commentDto = convertInputToDto(comment);
        return commentService.createComment(commentDto);
    }

    @MutationMapping
    public Boolean deleteComment(@Argument Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @SchemaMapping(typeName = "Comment")
    public RecipeDto recipe(CommentDto comment) {
        return recipeService.getRecipe(comment.getRecipeId(), null);
    }

    private CommentDto convertInputToDto(CommentInput input) {
        CommentDto dto = new CommentDto();
        dto.setText(input.getText());
        dto.getUser().setUserId(input.getUserId());
        dto.setRecipeId(input.getRecipeId());
        return dto;
    }
} 