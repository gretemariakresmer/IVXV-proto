package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.election.Party;
import ee.taltech.ivxvproto.repository.PartyRepository;
import ee.taltech.ivxvproto.service.GetPartyById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPartyByIdImpl implements GetPartyById {

    private final PartyRepository repository;
    @Override
    public Party execute(long id) {
        return repository.getReferenceById(id);
    }
}
