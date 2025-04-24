package ee.taltech.ivxvproto.model.vote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {
    Long Id;
    String Name;
}
