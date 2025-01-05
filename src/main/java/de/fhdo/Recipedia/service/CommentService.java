package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto);
    Boolean deleteComment(Long commentId);
    List<CommentDto> getCommentsByRecipe(Long recipeId);
}
