package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.election.Person;

public interface GetPersonById {

    Person execute(long id);
}
