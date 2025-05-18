package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.TemporaryVote;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.repository.TemporaryVoteRepository;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.BroadcastVote;
import ee.taltech.ivxvproto.service.ConfirmVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ConfirmVoteImpl implements ConfirmVote {
    private final TemporaryVoteRepository temporaryVoteRepository;
    private final VoteRepository repository;
    private final BroadcastVote broadcastVote;

    @Override
    public VoteResponseDto execute(String previewToken) throws Exception {
        TemporaryVote temporaryVote = temporaryVoteRepository.findById(UUID.fromString(previewToken))
                .orElseThrow(() -> new IllegalArgumentException("Preview token not found"));

        Vote vote = Vote.builder()
                .candidateId(temporaryVote.getCandidateId())
                .generatedNumber(temporaryVote.getGeneratedNumber())
                .creationTime(LocalDateTime.now())
                .ciphertext(temporaryVote.getCiphertext())
                .build();

        repository.save(vote);
        broadcastVote.execute(BlockDto.toDto(vote));

        temporaryVoteRepository.delete(temporaryVote);
        return VoteResponseDto.of(vote.getCiphertext());
    }
}
