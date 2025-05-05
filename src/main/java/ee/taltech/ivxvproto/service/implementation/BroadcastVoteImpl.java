package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.service.BroadcastVote;
import ee.taltech.ivxvproto.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BroadcastVoteImpl implements BroadcastVote {

    private final StreamingService streamingService;

    @Override
    public void execute(BlockDto vote) {
        streamingService.broadcast(vote);
    }
}
