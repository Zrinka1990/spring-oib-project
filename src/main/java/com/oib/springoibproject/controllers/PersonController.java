package com.oib.springoibproject.controllers;

import com.oib.springoibproject.commands.PersonCommand;
import com.oib.springoibproject.commands.PersonCommandToPerson;
import com.oib.springoibproject.dao.PersonJDBCTemplate;
import com.oib.springoibproject.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-application/person")
public class PersonController {
    private PersonJDBCTemplate personJDBCTemplate;

    public PersonController(PersonJDBCTemplate personJDBCTemplate) {
        this.personJDBCTemplate = personJDBCTemplate;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personJDBCTemplate.getAll();
    }

    @GetMapping("/{oib}")
    public Person getPersonByOib(@PathVariable String oib) {
        return personJDBCTemplate.getByOib(oib);
    }

    @PostMapping("/{oib}")
    public String createPerson(@PathVariable String oib, @RequestBody PersonCommand personCommand) {
        personJDBCTemplate.create(oib, personCommand);
        return "Person updated";
    }
}
