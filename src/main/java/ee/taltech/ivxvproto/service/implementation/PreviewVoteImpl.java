package ee.taltech.ivxvproto.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.ivxvproto.config.JsonConfig;
import ee.taltech.ivxvproto.crypting.Encryptor;
import ee.taltech.ivxvproto.model.EncryptionPayload;
import ee.taltech.ivxvproto.model.dto.EncryptPreviewDto;
import ee.taltech.ivxvproto.repository.TemporaryVoteRepository;
import ee.taltech.ivxvproto.service.PreviewVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class PreviewVoteImpl implements PreviewVote {

    private final TemporaryVoteRepository tempRepo;
    private final Encryptor encryptor;
    private final ObjectMapper mapper = JsonConfig.createMapper();
    private final SecureRandom secureRandom = new SecureRandom();
    @Override
    public EncryptPreviewDto execute(long candidateId) throws Exception {
        long generated = secureRandom.nextLong() & Long.MAX_VALUE;

        EncryptionPayload payload = EncryptionPayload.builder()
                .selectedCandidateId(candidateId)
                .generatedNumber(generated)
                .build();

        String json = mapper.writeValueAsString(payload);
        String cipher = encryptor.encrypt(json);

        String token = tempRepo.createTemporaryRecord(candidateId, generated, cipher);

        return EncryptPreviewDto.builder()
                .previewToken(token)
                .ciphertext(cipher)
                .build();
    }
}
