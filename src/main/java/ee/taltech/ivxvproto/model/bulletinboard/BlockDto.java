package ee.taltech.ivxvproto.model.bulletinboard;

import ee.taltech.ivxvproto.model.vote.Vote;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlockDto {
    String ciphertext;
    LocalDateTime creationTime;

    public static BlockDto toDto(Vote vote) {
        return BlockDto.builder()
                .ciphertext(vote.getCiphertext())
                .creationTime(vote.getCreationTime())
                .build();
    }
}
