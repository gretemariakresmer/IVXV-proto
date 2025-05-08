package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;

public interface BroadcastVote {
    void execute(BlockDto vote);
}
