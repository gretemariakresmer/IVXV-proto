package ee.taltech.ivxvproto.controller;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.model.vote.VoteRequest;
import ee.taltech.ivxvproto.service.GetVotes;
import ee.taltech.ivxvproto.service.RegisterVoteStream;
import ee.taltech.ivxvproto.service.SaveVote;
import ee.taltech.ivxvproto.service.SearchVotes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final SaveVote saveVote;
    private final GetVotes getVotes;
    private final SearchVotes searchVotes;
    private final RegisterVoteStream registerVoteStream;

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


    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long id) {
        return null;
    }

    @PostMapping()
    public VoteResponseDto saveVote(@RequestBody VoteRequest request) throws Exception {
        return saveVote.execute(request);
    }
}
