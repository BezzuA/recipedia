package de.fhdo.Recipedia.converter;

import de.fhdo.Recipedia.entity.Reply;
import de.fhdo.Recipedia.dto.ReplyDto;
import org.springframework.stereotype.Component;

@Component
public class ReplyConverter {

    public ReplyDto toDto(Reply reply) {
        ReplyDto replyDto = new ReplyDto();

        replyDto.setReplyId(reply.getReplyId());
        replyDto.setText(reply.getText());
        replyDto.setCreationDate(reply.getCreationDate());
        replyDto.setDiscussionId(reply.getDiscussion().getDiscussionId());

        replyDto.getUser().setUserId(reply.getUser().getUserId());
        replyDto.getUser().setUsername(reply.getUser().getUsername());

        return replyDto;
    }

    public Reply toEntity(ReplyDto replyDto) {
        Reply reply = new Reply();

        reply.setText(replyDto.getText());

        return reply;
    }
}
