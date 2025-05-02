package ee.taltech.ivxvproto.repository;

import ee.taltech.ivxvproto.model.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
