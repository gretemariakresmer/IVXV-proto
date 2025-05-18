package ee.taltech.ivxvproto.model.vote;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemporaryVote {
    @Id
    @GeneratedValue()
    UUID id;

    long candidateId;
    String ciphertext;
    long generatedNumber;
}
