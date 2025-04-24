package ee.taltech.ivxvproto.service;

import ee.taltech.ivxvproto.model.vote.Vote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {
    private final List<Vote> users = new ArrayList<>();

    public Optional<Vote> getVoteById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
