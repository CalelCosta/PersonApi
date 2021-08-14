package br.com.dio.personapi.controllers;

import br.com.dio.personapi.dto.PersonDTO;
import br.com.dio.personapi.entities.Person;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> getAllPersons(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id) throws Exception {
        return personService.findById(id).orElseThrow(() -> new Exception("Person not found!"));
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") Long id) throws Exception {
        try {
            personService.deletePerson(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ResponseEntity<Person>) ResponseEntity.ok();
    }
}
