package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;

import java.util.List;

public interface GetVotes {
    List<BlockDto> execute();
}
