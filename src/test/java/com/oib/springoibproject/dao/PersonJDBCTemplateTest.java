package com.oib.springoibproject.dao;

import com.oib.springoibproject.commands.PersonCommand;
import com.oib.springoibproject.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ComponentScan
class PersonJDBCTemplateTest {

    @Autowired
    private PersonJDBCTemplate personJDBCTemplate;

    @Test
    void getAll() {
        List<Person> allPersons = personJDBCTemplate.getAll();
        assertEquals(3, allPersons.size());
    }

    @Test
    void getByOib() {
        Person personReturned = personJDBCTemplate.getByOib("55957538755");
        assertNotNull(personReturned);
        assertEquals("55957538755", personReturned.getOib());

    }

    @Test
    void createOrUpdate() {
        PersonCommand personCommand = new PersonCommand();
        personCommand.setFirstName("Bob");
        personCommand.setLastName("Marley");
        personJDBCTemplate.createOrUpdate("45645645645", personCommand);
        Person result = personJDBCTemplate.getByOib("45645645645");
        assertNotNull(result);
        assertEquals(result.getFirstName(), "Bob");
        assertEquals(result.getLastName(), "Marley");
    }
}
