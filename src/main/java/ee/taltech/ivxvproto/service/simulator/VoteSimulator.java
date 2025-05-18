package ee.taltech.ivxvproto.service.simulator;

import ee.taltech.ivxvproto.crypting.Encryptor;
import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.BroadcastVote;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@ConditionalOnProperty(
        name = "simulate.votes.enabled",
        havingValue = "true",
        matchIfMissing = false
)
@Component
@RequiredArgsConstructor
public class VoteSimulator {
    private final Encryptor encryptor;
    private final BroadcastVote broadcastVote;
    private final VoteRepository repository;

    @PostConstruct
    public void clearOldSimulatedVotes() {
        repository.deleteAllSimulatedVotes();
    }

    @Scheduled(initialDelayString = "${simulate.votes.initial-delay:1000}", fixedRateString = "${simulate.votes.rate:5000}")
    public void simulate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String input = UUID.randomUUID().toString();
        String ciphertext = encryptor.encrypt(input);

        Vote vote = Vote.builder()
                .ciphertext(ciphertext)
                .creationTime(now)
                .simulated(true)
                .build();
        repository.save(vote);

        broadcastVote.execute(BlockDto.toDto(vote));
    }
}
