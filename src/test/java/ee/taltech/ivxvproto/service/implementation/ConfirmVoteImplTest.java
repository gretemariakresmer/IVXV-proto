package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.TemporaryVote;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.repository.TemporaryVoteRepository;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.BroadcastVote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmVoteImplTest {

    private static final String TOKEN = UUID.randomUUID().toString();
    private static final UUID ID = UUID.fromString(TOKEN);
    private static final Long CANDIDATE_ID = 42L;
    private static final Long GENERATED_NR = 123456L;
    private static final String ENCRYPTION = "encrypted-blob";

    @Mock
    private TemporaryVoteRepository temporaryVoteRepository;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private BroadcastVote broadcastVote;

    private ConfirmVoteImpl confirmVote;

    @BeforeEach
    void setUp() {
        confirmVote = new ConfirmVoteImpl(
                temporaryVoteRepository,
                voteRepository,
                broadcastVote
        );
    }

    @Test
    void execute_withValidToken_makesAllCalls() throws Exception {
        TemporaryVote temp = TemporaryVote.builder()
                .id(ID)
                .candidateId(CANDIDATE_ID)
                .generatedNumber(GENERATED_NR)
                .ciphertext(ENCRYPTION)
                .build();
        when(temporaryVoteRepository.findById(ID)).thenReturn(Optional.ofNullable(temp));

        VoteResponseDto dto = confirmVote.execute(TOKEN);

        assertNotNull(dto);
        assertEquals(ENCRYPTION, dto.getCipher());

        ArgumentCaptor<Vote> voteCaptor = ArgumentCaptor.forClass(Vote.class);
        verify(voteRepository).save(voteCaptor.capture());
        Vote saved = voteCaptor.getValue();

        assertEquals(CANDIDATE_ID, saved.getCandidateId());
        assertEquals(GENERATED_NR, saved.getGeneratedNumber());
        assertEquals(ENCRYPTION, saved.getCiphertext());
        assertNotNull(saved.getCreationTime());

        verify(broadcastVote).execute(BlockDto.toDto(saved));
        verify(temporaryVoteRepository).delete(temp);
    }

    @Test
    void execute_withUnknownToken_throws() {
        when(temporaryVoteRepository.findById(ID))
                .thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> confirmVote.execute(TOKEN));

        assertEquals("Preview token not found", ex.getMessage());
        verifyNoInteractions(voteRepository, broadcastVote);
    }
}