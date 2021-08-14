package br.com.dio.personapi.controllers;

import br.com.dio.personapi.dto.PersonDTO;
import br.com.dio.personapi.exception.PersonNotFoundException;
import br.com.dio.personapi.message.MessageDTO;
import br.com.dio.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public PersonDTO getPersonById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageDTO updatePerson(@PathVariable("id") Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updatePerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") Long id) throws PersonNotFoundException {
        personService.deletePerson(id);
    }
}
