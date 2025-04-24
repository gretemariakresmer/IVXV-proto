package ee.taltech.ivxvproto.model.dto;

import ee.taltech.ivxvproto.model.election.Party;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartyDto {
    Long id;
    String name;

    public static PartyDto toDto(Party party) {
        return PartyDto.builder()
                .id(party.getId())
                .name(party.getName())
                .build();
    }
}
