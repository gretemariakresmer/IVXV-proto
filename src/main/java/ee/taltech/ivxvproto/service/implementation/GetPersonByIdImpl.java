package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.election.Person;
import ee.taltech.ivxvproto.repository.PersonRepository;
import ee.taltech.ivxvproto.service.GetPersonById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPersonByIdImpl implements GetPersonById {
    private final PersonRepository repository;
    @Override
    public Person execute(long id) {
        return repository.getReferenceById(id);
    }
}
