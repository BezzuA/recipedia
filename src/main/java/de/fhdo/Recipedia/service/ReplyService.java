package de.fhdo.Recipedia.service;

import de.fhdo.Recipedia.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    ReplyDto createReply(ReplyDto replyDto);
    Boolean deleteReply(Long replyId);
    List<ReplyDto> getRepliesByDiscussion(Long discussionId);
    List<ReplyDto> getRepliesByUser(Long userId);
}
