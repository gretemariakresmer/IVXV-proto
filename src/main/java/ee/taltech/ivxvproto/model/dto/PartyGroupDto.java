package ee.taltech.ivxvproto.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PartyGroupDto {
    String party;
    List<PersonDto> candidates;
}
