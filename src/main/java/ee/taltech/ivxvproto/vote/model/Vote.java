package ee.taltech.ivxvproto.vote.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vote {
    Long Id;
    String Name;
}
