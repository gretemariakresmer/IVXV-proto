package ee.taltech.ivxvproto.service.implementation;

import ee.taltech.ivxvproto.model.bulletinboard.BlockDto;
import ee.taltech.ivxvproto.repository.VoteRepository;
import ee.taltech.ivxvproto.service.GetBulletinBoardData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetBulletinBoardDataImpl implements GetBulletinBoardData {
    private final VoteRepository repository;
    @Override
    public List<BlockDto> execute() {
        return repository.findAll().stream()
                .map(BlockDto::toDto)
                .toList();
    }
}
