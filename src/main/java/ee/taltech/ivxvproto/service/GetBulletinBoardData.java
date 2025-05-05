package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;

import java.util.List;

public interface GetBulletinBoardData {
    List<BlockDto> execute();
}
