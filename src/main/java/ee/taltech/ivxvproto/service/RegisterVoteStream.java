package ee.taltech.ivxvproto.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface RegisterVoteStream {
    SseEmitter execute();
}
