package com.oib.springoibproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.oib.springoibproject.model.Person;
import org.springframework.jdbc.core.RowMapper;

public class PersonMapper implements RowMapper<Person> {
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setOib(resultSet.getString("oib"));
        person.setFirstName(resultSet.getString("firstName"));
        person.setLastName((resultSet.getString("lastName")));
        return person;
    }
}