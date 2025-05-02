package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.repository.PersonRepository;
import ee.taltech.ivxvproto.service.GetPartyCodeByPersonId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPartyCodeByPersonIdImpl implements GetPartyCodeByPersonId {
    private final PersonRepository repository;
    @Override
    public long execute(long id) {
        return repository.findPartyCodeById(id);
    }
}
