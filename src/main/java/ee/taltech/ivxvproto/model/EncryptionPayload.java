package ee.taltech.ivxvproto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EncryptionPayload {
    long selectedCandidateId;
    long generatedNumber;
}
