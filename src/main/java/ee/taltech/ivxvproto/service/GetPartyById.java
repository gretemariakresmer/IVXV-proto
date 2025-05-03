package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.election.Party;

public interface GetPartyById {
    Party execute(long id);
}
