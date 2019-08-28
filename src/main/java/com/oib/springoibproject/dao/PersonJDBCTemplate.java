package com.oib.springoibproject.dao;

import com.oib.springoibproject.exceptions.NotFoundException;
import com.oib.springoibproject.mapper.PersonMapper;
import com.oib.springoibproject.model.Person;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public Person getByOib(String oib) {
        String sqlQuery = "select * from person where oib = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{oib}, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Person with oib " + oib + " not found!");
        }
    }
}
