package com.oib.springoibproject.dao;

import com.oib.springoibproject.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ComponentScan
class PersonJDBCTemplateTest {

    @Autowired
    private PersonJDBCTemplate personJDBCTemplate;

    @Test
    void getAll() {
        List<Person> all = personJDBCTemplate.getAll();
        assertEquals(3, all.size());
    }

    @Test
    void getByOib() {
    }

    @Test
    void createOrUpdate() {

    }
}