package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.ReplyConverter;
import de.fhdo.Recipedia.dto.ReplyDto;
import de.fhdo.Recipedia.entity.Discussion;
import de.fhdo.Recipedia.entity.Reply;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.DiscussionRepository;
import de.fhdo.Recipedia.repository.ReplyRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final DiscussionRepository discussionRepository;
    private final ReplyConverter replyConverter;

    public ReplyServiceImpl(ReplyRepository replyRepository,
                            UserRepository userRepository,
                            DiscussionRepository discussionRepository,
                            ReplyConverter replyConverter) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.discussionRepository = discussionRepository;
        this.replyConverter = replyConverter;
    }

    @Override
    @Transactional
    public ReplyDto addReply(ReplyDto replyDto) {
        User user = userRepository.findById(replyDto.getUser().getUserId()).orElse(null);
        Discussion discussion = discussionRepository.findById(replyDto.getDiscussionId()).orElse(null);

        if (user == null || discussion == null) {
            return null;
        }

        Reply reply = replyConverter.toEntity(replyDto);
        reply.setCreationTime(Timestamp.from(Instant.now()));
        reply.setUser(user);
        reply.setDiscussion(discussion);

        replyRepository.save(reply);

        return replyConverter.toDto(reply);
    }

    @Override
    @Transactional
    public Boolean deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElse(null);

        if (reply == null) {
            return false;
        }

        replyRepository.delete(reply);

        return true;
    }

    @Override
    @Transactional
    public List<ReplyDto> getRepliesByDiscussion(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElse(null);

        if (discussion == null) {
            return null;
        }

        List<Reply> replies = discussion.getReplies();


        return replies.stream().map(replyConverter::toDto).toList();
    }
}
