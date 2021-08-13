package br.com.dio.personapi.services;



import br.com.dio.personapi.entities.Person;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
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

    public MessageDTO createPerson(Person person) {
        Person savePerson = personRepository.save(person);
        return MessageDTO
                .builder()
                .message("Person created with ID " + savePerson.getId())
                .build();
    }
}
