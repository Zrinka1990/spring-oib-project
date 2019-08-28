package com.oib.springoibproject.dao;

import com.oib.springoibproject.commands.PersonCommand;
import com.oib.springoibproject.model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getAll();

    Person getByOib(String oib);

    void createOrUpdate(String oib, PersonCommand command);
}
