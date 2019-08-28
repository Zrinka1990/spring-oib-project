package com.oib.springoibproject.controller;

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
        return personJDBCTemplate.getAllPersons();
    }

    @GetMapping("/{oib}")
    public Person getPersonByOib(@PathVariable String oib) {
        return personJDBCTemplate.getByOib(oib);
    }
}
