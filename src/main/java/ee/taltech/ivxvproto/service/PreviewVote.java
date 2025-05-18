package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.dto.EncryptPreviewDto;

public interface PreviewVote {

    EncryptPreviewDto execute(long candidateId) throws Exception;
}
