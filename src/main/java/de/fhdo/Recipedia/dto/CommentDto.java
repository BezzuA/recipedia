package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Comment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private Long commentId;
    private String text;
    private Date creationDate;
    private UserDto user = new UserDto();
    private Long recipeId;
}