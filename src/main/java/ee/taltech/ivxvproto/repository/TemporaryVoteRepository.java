package ee.taltech.ivxvproto.repository;

import ee.taltech.ivxvproto.model.vote.TemporaryVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TemporaryVoteRepository extends JpaRepository<TemporaryVote, UUID> {

    default String createTemporaryRecord(long candidateId, long generatedNumber, String cipherText) {
        TemporaryVote temp = TemporaryVote.builder()
                .generatedNumber(generatedNumber)
                .candidateId(candidateId)
                .ciphertext(cipherText)
                .build();
        temp = save(temp);
        return temp.getId().toString();
    }
}
