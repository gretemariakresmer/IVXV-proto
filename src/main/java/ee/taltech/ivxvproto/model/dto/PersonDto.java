package ee.taltech.ivxvproto.model.dto;

import ee.taltech.ivxvproto.model.election.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    Long id;
    String firstName;
    String lastName;

    public static PersonDto toDto(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .build();
    }
}
