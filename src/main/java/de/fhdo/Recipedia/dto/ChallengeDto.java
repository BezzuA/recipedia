package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Challenge}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDto implements Serializable {
    private Long challengeId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<Long> userIds;
    private List<RecipeDto> recipes;
}