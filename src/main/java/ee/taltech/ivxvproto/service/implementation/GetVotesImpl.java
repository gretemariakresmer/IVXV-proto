package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.GetVotes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetVotesImpl implements GetVotes {

    private final VoteRepository repository;
    @Override
    public List<BlockDto> execute() {
        return repository.findAll(Sort.by("creationTime").descending())
                .stream()
                .map(BlockDto::toDto)
                .toList();
    }
}
