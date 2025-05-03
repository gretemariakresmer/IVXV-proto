package ee.taltech.ivxvproto.repository;

import ee.taltech.ivxvproto.model.election.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findPersonsByPartyCode(long partyCode);

    @Query("SELECT partyCode FROM Person WHERE id = :id")
    Long findPartyCodeById(long id);
}
