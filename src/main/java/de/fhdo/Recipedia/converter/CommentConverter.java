package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setCommentId(comment.getCommentId());
        commentDto.setText(comment.getText());
        commentDto.setCreationDate(comment.getCreationDate());
        commentDto.getUser().setUserId(comment.getUser().getUserId());
        commentDto.getUser().setUsername(comment.getUser().getUsername());
        commentDto.setRecipeId(comment.getRecipe().getRecipeId());

        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setText(commentDto.getText());

        return comment;
    }
}
