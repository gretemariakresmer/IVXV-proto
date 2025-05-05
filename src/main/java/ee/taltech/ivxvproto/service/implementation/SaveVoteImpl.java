package ee.taltech.ivxvproto.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.ivxvproto.config.JsonConfig;
import ee.taltech.ivxvproto.crypting.Encryptor;
import ee.taltech.ivxvproto.model.EncryptionPayload;
import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.model.vote.VoteRequest;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.BroadcastVote;
import ee.taltech.ivxvproto.service.GetPartyCodeByPersonId;
import ee.taltech.ivxvproto.service.SaveVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class SaveVoteImpl implements SaveVote {
    private final VoteRepository repository;
    private final Encryptor encryptor;
    private final GetPartyCodeByPersonId getPartyCodeByPersonId;
    private final BroadcastVote broadcastVote;

    private final ObjectMapper mapper = JsonConfig.createMapper();
    private final List<Vote> store = new CopyOnWriteArrayList<>();
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public VoteResponseDto execute(VoteRequest voteRequest) throws Exception {
        long generatedNumber = secureRandom.nextLong() & Long.MAX_VALUE;
        LocalDateTime now = LocalDateTime.now();
        long partyCode = getPartyCodeByPersonId.execute(voteRequest.getPersonId());

        EncryptionPayload payload = createEncryptionPayload(voteRequest, generatedNumber, now, partyCode);
        String json = mapper.writeValueAsString(payload);
        String cipher = encryptor.encrypt(json);

        Vote vote = Vote.builder()
                .candidateId(voteRequest.getPersonId())
                .partyId(partyCode)
                .generatedNumber(generatedNumber)
                .creationTime(now)
                .ciphertext(cipher)
                .build();

        repository.save(vote);
        broadcastVote.execute(BlockDto.toDto(vote));

        return VoteResponseDto.of(cipher);
    }

    private EncryptionPayload createEncryptionPayload(VoteRequest request, long generatedRandom, LocalDateTime now, long partyId) {
        return EncryptionPayload.builder()
                .selectedPersonId(request.getPersonId())
                .selectedPartyId(partyId)
                .generatedNumber(generatedRandom)
                .creationTime(now)
                .build();
    }
}
