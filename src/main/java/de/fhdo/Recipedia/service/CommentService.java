package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    Boolean deleteComment(Long commentId);
    List<CommentDto> getCommentsByRecipe(Long recipeId);
    List<CommentDto> getCommentsByUser(Long userId);
}
