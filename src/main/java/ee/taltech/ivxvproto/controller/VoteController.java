package ee.taltech.ivxvproto.controller;

import ee.taltech.ivxvproto.model.dto.VoteResponseDto;
import ee.taltech.ivxvproto.model.vote.Vote;
import ee.taltech.ivxvproto.model.vote.VoteRequest;
import ee.taltech.ivxvproto.service.SaveVote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final SaveVote saveVote;

    @GetMapping
    public List<Vote> getVotes() {
        //return List.of(Vote.builder().Id(-100L).Name("name").build());'
        return null;
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
