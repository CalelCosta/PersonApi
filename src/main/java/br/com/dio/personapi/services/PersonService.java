package br.com.dio.personapi.services;



import br.com.dio.personapi.dto.PersonDTO;
import br.com.dio.personapi.entities.Person;
import br.com.dio.personapi.mapper.PersonMapper;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findAll() {
        List<Person> getAllPersons = personRepository.findAll();
        return getAllPersons.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public MessageDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);
        return MessageDTO
                .builder()
                .message("Person created with ID " + savePerson.getId())
                .build();
    }
}
