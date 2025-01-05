package de.fhdo.Recipedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * DTO for {@link de.fhdo.Recipedia.entity.Reply}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto implements Serializable {
    private Long replyId;
    private String text;
    private Timestamp creationTime;
    private UserDto user = new UserDto();
    private Long discussionId;
}