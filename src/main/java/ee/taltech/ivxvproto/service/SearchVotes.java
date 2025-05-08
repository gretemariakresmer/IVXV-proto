package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import lombok.Value;

import java.util.List;

public interface SearchVotes {
    List<BlockDto> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        String term;
    }
}
