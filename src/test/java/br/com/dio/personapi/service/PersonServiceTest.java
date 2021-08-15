package br.com.dio.personapi.service;

import br.com.dio.personapi.dto.PersonDTO;
import br.com.dio.personapi.entities.Person;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.repositories.PersonRepository;
import br.com.dio.personapi.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.dio.personapi.utils.PersonUtils.createFakeDTO;
import static br.com.dio.personapi.utils.PersonUtils.createFakeEntity;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageDTO expectedSuccessMessage = MessageDTO
                .builder()
                .message("Person created with ID " + expectedSavedPerson.getId())
                .build();

        MessageDTO successMessage = personService.createPerson(personDTO);
    }
}
