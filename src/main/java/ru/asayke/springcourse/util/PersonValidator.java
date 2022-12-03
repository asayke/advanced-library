package ru.asayke.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.asayke.springcourse.models.Person;
import ru.asayke.springcourse.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        Optional<Person> validatingPerson = peopleService.getPersonByFullName(person.getName());

        if(validatingPerson.isPresent())
            errors.rejectValue("name", "", "Человек с таким именем уже сущетсвует");
    }
}
