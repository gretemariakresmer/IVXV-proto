package ee.taltech.ivxvproto.controller;

import ee.taltech.ivxvproto.model.dto.PartyGroupDto;
import ee.taltech.ivxvproto.service.GetPartiesWithCandidates;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
@RequiredArgsConstructor
public class PartyController {
    private final GetPartiesWithCandidates getPartiesWithCandidates;

    @GetMapping("/candidates")
    public List<PartyGroupDto> getPartiesWithCandidates() {
        return getPartiesWithCandidates.execute();
    }
}
