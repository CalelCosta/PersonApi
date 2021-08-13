package br.com.dio.personapi.controllers;

import br.com.dio.personapi.dto.PersonDTO;
import br.com.dio.personapi.entities.Person;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public MessageDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @GetMapping
    public List<Person> getPerson(){
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
