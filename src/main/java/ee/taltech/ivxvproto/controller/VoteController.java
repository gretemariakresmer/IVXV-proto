package ee.taltech.ivxvproto.controller;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.dto.EncryptPreviewDto;
import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final ConfirmVote confirmVote;
    private final GetVotes getVotes;
    private final SearchVotes searchVotes;
    private final RegisterVoteStream registerVoteStream;
    private final PreviewVote previewVote;

    @GetMapping
    public List<BlockDto> getVotes() {
        return getVotes.execute();
    }

    @GetMapping("/search")
    public List<BlockDto> search(@RequestParam String term) {
        return searchVotes.execute(SearchVotes.Request.of(term));
    }

    @GetMapping("/stream")
    public SseEmitter stream() {
        return registerVoteStream.execute();
    }

    @PostMapping()
    public VoteResponseDto saveVote(@RequestParam String previewToken) throws Exception {
        return confirmVote.execute(previewToken);
    }

    @PostMapping("/preview")
    public EncryptPreviewDto preview(@RequestParam long candidate) throws Exception {
        return previewVote.execute(candidate);
    }
}
