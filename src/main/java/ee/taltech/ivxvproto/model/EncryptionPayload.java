package ee.taltech.ivxvproto.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EncryptionPayload {
    long selectedPersonId;
    long selectedPartyId;
    long generatedNumber;
    LocalDateTime creationTime;
}
