package com.oib.springoibproject.commands;

import com.oib.springoibproject.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PersonCommandToPerson implements Converter<PersonCommand, Person> {
   @Nullable
    @Override
    public Person convert(PersonCommand personCommand) {
        if (personCommand == null) {
            return null;
        }
        final Person person = new Person();
        person.setFirstName(personCommand.getFirstName());
        person.setLastName(personCommand.getLastName());
        return person;
    }
}
