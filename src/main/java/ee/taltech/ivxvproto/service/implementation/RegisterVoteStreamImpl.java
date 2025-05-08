package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.service.RegisterVoteStream;
import ee.taltech.ivxvproto.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class RegisterVoteStreamImpl implements RegisterVoteStream {
    private final StreamingService streamingService;

    @Override
    public SseEmitter execute() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        streamingService.register(emitter);
        return emitter;
    }
}
