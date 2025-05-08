package ee.taltech.ivxvproto.repository;

import ee.taltech.ivxvproto.model.vote.Vote;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByCiphertextContainingIgnoreCase(String term);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote WHERE simulated = true")
    void deleteAllSimulatedVotes();
}
