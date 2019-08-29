package com.oib.springoibproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    private Person person;

    @BeforeEach
    void setUp(){
        person = new Person();
    }

    @Test
    void getOib() {
        String oibValue = "xxxxxxxxxxx";
        person.setOib(oibValue);
        assertEquals(oibValue, person.getOib());
    }

    @Test
    void getFirstName() {
        String firstNameValue = "First";
        person.setFirstName(firstNameValue);
        assertEquals(firstNameValue, person.getFirstName());
    }

    @Test
    void getLastName() {
        String lastNameValue = "Last";
        person.setLastName(lastNameValue);
        assertEquals(lastNameValue, person.getLastName());
    }
}

