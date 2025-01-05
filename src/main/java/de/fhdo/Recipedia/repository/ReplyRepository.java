package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.Discussion;
import de.fhdo.Recipedia.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByDiscussion(Discussion discussion);
}