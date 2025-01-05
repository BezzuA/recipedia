package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long userId;
    private String username;
    private String email;
    private String bio;
    private List<Long> recipeIds;
    private List<Long> discussionIds;
    private List<Long> challengeIds;
}