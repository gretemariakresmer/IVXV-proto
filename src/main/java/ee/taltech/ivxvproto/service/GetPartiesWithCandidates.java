package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.dto.PartyGroupDto;

import java.util.List;

public interface GetPartiesWithCandidates {
    List<PartyGroupDto> execute();
}
