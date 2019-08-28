package com.oib.springoibproject.dao;

import com.oib.springoibproject.commands.PersonCommand;
import com.oib.springoibproject.commands.PersonCommandToPerson;
import com.oib.springoibproject.exceptions.NotFoundException;
import com.oib.springoibproject.mapper.PersonMapper;
import com.oib.springoibproject.model.Person;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PersonJDBCTemplate implements PersonDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PersonCommandToPerson personConverter;

    public PersonJDBCTemplate(DataSource dataSource, JdbcTemplate jdbcTemplate, PersonCommandToPerson personConverter) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.personConverter = personConverter;
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public Person getByOib(String oib) {
        String sqlQuery = "select * from person where oib = ?";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{oib}, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("404: Person with oib " + oib + " not found!");
        }
    }

    @Transactional
    @Override
    public void createOrUpdate(String oib, PersonCommand command) {
        Person person = personConverter.convert(command);
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String sql = "SELECT count(*) FROM person WHERE oib = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[] { oib }, Integer.class);
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
