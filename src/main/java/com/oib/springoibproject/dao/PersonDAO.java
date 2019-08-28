package com.oib.springoibproject.dao;

import com.oib.springoibproject.model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getAllPersons();

    Person getByOib(String oib);
}
