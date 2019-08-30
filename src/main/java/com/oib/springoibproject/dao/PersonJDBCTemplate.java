package com.oib.springoibproject.dao;

import com.oib.springoibproject.commands.PersonCommand;
import com.oib.springoibproject.exceptions.NotFoundException;
import com.oib.springoibproject.mapper.PersonMapper;
import com.oib.springoibproject.model.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
public class PersonJDBCTemplate implements PersonDAO {
    private JdbcTemplate jdbcTemplate;

    public PersonJDBCTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    @Override
    public Person getByOib(String oib) {
        String sqlQuery = "SELECT * FROM person where oib = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{oib}, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Person with the oib " + oib + " not found!");
        }
    }

    @Transactional
    @Override
    public void createOrUpdate(String oib, PersonCommand command) {
        if (oib.length() != 11 || !oib.matches("[0-9]+")) {
            throw new IllegalArgumentException("OIB length should be 11 and digits only");
        }
        String firstName = command.getFirstName();
        String lastName = command.getLastName();
        String sql = "SELECT COUNT(*) FROM person WHERE oib = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{oib}, Integer.class);
        assert count != null;
        if (count > 0) {
            jdbcTemplate.update("UPDATE Person SET firstName = ?, lastName = ?" +
                    " WHERE oib = ?", firstName, lastName, oib);
        } else {
            jdbcTemplate.update("insert into person (oib, firstname, lastName) values (?, ?, ?)",
                    oib, firstName, lastName);
        }
    }
}
