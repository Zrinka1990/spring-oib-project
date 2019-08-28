package com.oib.springoibproject.dao;

import com.oib.springoibproject.mapper.PersonMapper;
import com.oib.springoibproject.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class PersonJDBCTemplate implements PersonDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public PersonJDBCTemplate(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = jdbcTemplate.query("select * from person", new PersonMapper());
        return persons;
    }

}
