package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.CommentConverter;
import de.fhdo.Recipedia.dto.CommentDto;
import de.fhdo.Recipedia.entity.Comment;
import de.fhdo.Recipedia.entity.Recipe;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.CommentRepository;
import de.fhdo.Recipedia.repository.RecipeRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final CommentConverter commentConverter;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, RecipeRepository recipeRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.commentConverter = commentConverter;
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {
        User user = userRepository.findById(commentDto.getUser().getUserId()).orElse(null);
        Recipe recipe = recipeRepository.findById(commentDto.getRecipeId()).orElse(null);

        if(user == null || recipe == null) {
            return null;
        }

        Comment comment = commentConverter.toEntity(commentDto);
        comment.setCreationDate(new Date());
        comment.setUser(user);
        comment.setRecipe(recipe);

        commentRepository.save(comment);


        return commentConverter.toDto(comment);
    }

    @Override
    @Transactional
    public Boolean deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if(comment == null) {
            return false;
        }

        commentRepository.delete(comment);

        return true;
    }

    @Override
    @Transactional
    public List<CommentDto> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if(recipe == null) {
            return List.of();
        }

        List<Comment> comments = commentRepository.findByRecipe(recipe);


        return comments.stream().map(commentConverter::toDto).toList();
    }
}
