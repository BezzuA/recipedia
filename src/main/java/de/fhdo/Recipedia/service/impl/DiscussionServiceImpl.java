package de.fhdo.Recipedia.service.impl;

import de.fhdo.Recipedia.converter.DiscussionConverter;
import de.fhdo.Recipedia.dto.DiscussionDto;
import de.fhdo.Recipedia.entity.Discussion;
import de.fhdo.Recipedia.entity.User;
import de.fhdo.Recipedia.repository.DiscussionRepository;
import de.fhdo.Recipedia.repository.ReplyRepository;
import de.fhdo.Recipedia.repository.UserRepository;
import de.fhdo.Recipedia.service.DiscussionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final DiscussionConverter discussionConverter;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository,
                                 ReplyRepository replyRepository,
                                 UserRepository userRepository,
                                 DiscussionConverter discussionConverter) {
        this.discussionRepository = discussionRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.discussionConverter = discussionConverter;
    }

    @Override
    @Transactional
    public DiscussionDto addDiscussion(DiscussionDto discussionDto) {
        User user = userRepository.findById(discussionDto.getUser().getUserId()).orElse(null);

        if (user == null) {
            return null;
        }

        Discussion discussion = discussionConverter.toEntity(discussionDto);
        discussion.setCreationDate(new Date());
        discussion.setUser(user);

        discussionRepository.save(discussion);


        return discussionConverter.toDto(discussion);
    }

    @Override
    @Transactional
    public Boolean deleteDiscussion(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElse(null);

        if (discussion == null) {
            return false;
        }

        discussionRepository.delete(discussion);

        return true;
    }

    @Override
    @Transactional
    public DiscussionDto getDiscussion(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElse(null);

        if (discussion == null) {
            return null;
        }


        return discussionConverter.toDto(discussion);
    }

    @Override
    @Transactional
    public List<DiscussionDto> getDiscussions() {
        List<Discussion> discussions = discussionRepository.findAllByOrderByCreationDateDesc();

        return addRepliesCount(discussions);
    }

    @Override
    @Transactional
    public List<DiscussionDto> getDiscussionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        List<Discussion> discussions = discussionRepository.findByUserOrderByCreationDateDesc(user);

        return addRepliesCount(discussions);
    }

    private List<DiscussionDto> addRepliesCount(List<Discussion> discussions) {
        return discussions.stream().map(discussion -> {
            DiscussionDto discussionDto = discussionConverter.toDto(discussion);
            int repliesCount = replyRepository.findByDiscussionOrderByCreationDateDesc(discussion).size();

            discussionDto.setRepliesCount(repliesCount);

            return discussionDto;
        }).toList();
    }
}
