package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.SearchVotes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchVotesImpl implements SearchVotes {

    private final VoteRepository repository;

    @Override
    public List<BlockDto> execute(Request request) {
        return repository
                .findByCiphertextContainingIgnoreCase(request.getTerm())
                .stream()
                .map(BlockDto::toDto)
                .toList();
    }
}
