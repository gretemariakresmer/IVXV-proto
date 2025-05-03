package ee.taltech.ivxvproto.model.dto;

import lombok.Data;
import lombok.Value;

@Data
@Value(staticConstructor = "of")
public class VoteResponseDto {
    String cipher;
}
