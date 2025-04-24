package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.dto.PartyGroupDto;
import ee.taltech.ivxvproto.model.dto.PersonDto;
import ee.taltech.ivxvproto.model.election.Party;
import ee.taltech.ivxvproto.repository.PartyRepository;
import ee.taltech.ivxvproto.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPartiesWithCandidatesImpl implements GetPartiesWithCandidates {
    private final PartyRepository partyRepository;
    private final PersonRepository personRepository;
    @Override
    public List<PartyGroupDto> execute() {
        List<Party> parties = partyRepository.findAll();
        return parties.stream()
                .map(party -> {
                    List<PersonDto> persons = personRepository.findPersonsByPartyCode(party.getId())
                            .stream()
                            .map(PersonDto::toDto)
                            .toList();
                    return PartyGroupDto.builder()
                            .party(party.getName())
                            .candidates(persons)
                            .build();
                }).toList();
    }
}
