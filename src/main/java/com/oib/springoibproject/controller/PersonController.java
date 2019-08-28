package com.oib.springoibproject.controller;

import com.oib.springoibproject.dao.PersonJDBCTemplate;
import com.oib.springoibproject.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
