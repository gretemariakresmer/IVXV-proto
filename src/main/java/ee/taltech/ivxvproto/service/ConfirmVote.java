package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.dto.VoteResponseDto;

public interface ConfirmVote {

    VoteResponseDto execute(String previewToken) throws Exception;
}
