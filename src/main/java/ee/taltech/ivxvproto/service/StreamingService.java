package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface StreamingService {
    void register(SseEmitter emitter);
    void broadcast(BlockDto vote);
}
