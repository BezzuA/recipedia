package de.fhdo.Recipedia.resolver;

import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.resolver.input.ReplyInput;
import de.fhdo.Recipedia.service.DiscussionService;
import de.fhdo.Recipedia.service.ReplyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReplyResolver {
    private final ReplyService replyService;
    private final DiscussionService discussionService;

    public ReplyResolver(ReplyService replyService, DiscussionService discussionService) {
        this.replyService = replyService;
        this.discussionService = discussionService;
    }

    @MutationMapping
    public ReplyDto createReply(@Argument ReplyInput reply) {
        ReplyDto replyDto = convertInputToDto(reply);
        return replyService.createReply(replyDto);
    }



    @MutationMapping
    public Boolean deleteReply(@Argument Long replyId) {
        return replyService.deleteReply(replyId);
    }

    @SchemaMapping(typeName = "Reply")
    public DiscussionDto discussion(ReplyDto reply) {
        return discussionService.getDiscussion(reply.getDiscussionId());
    }

    private ReplyDto convertInputToDto(ReplyInput input) {
        ReplyDto dto = new ReplyDto();
        dto.setText(input.getText());
        dto.getUser().setUserId(input.getUserId());
        dto.setDiscussionId(input.getDiscussionId());
        return dto;
    }

} 