package ee.taltech.ivxvproto.repository;

import ee.taltech.ivxvproto.model.election.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {

}
