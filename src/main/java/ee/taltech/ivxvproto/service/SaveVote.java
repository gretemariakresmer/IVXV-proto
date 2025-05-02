package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.VoteRequest;

public interface SaveVote {

    VoteResponseDto execute(VoteRequest voteRequest) throws Exception;
}
