package ee.taltech.ivxvproto.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EncryptPreviewDto {
    String previewToken;
    String ciphertext;
}
