package com.oib.springoibproject.dao;

import com.oib.springoibproject.model.Person;

import javax.sql.DataSource;
import java.util.List;

public interface PersonDAO {

    List<Person> getAllPersons();
}
