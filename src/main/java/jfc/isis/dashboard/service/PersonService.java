package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.PersonRepository;
import jfc.isis.dashboard.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonService {

    private final PersonRepository personDao;

    public PersonService(PersonRepository personRepository) {
        this.personDao = personRepository;
    }

    @Transactional
    public Person savePerson(String firstName, String lastName, Date birthday) {

        if(firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if(lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if(birthday == null ) {
            throw new IllegalArgumentException("Birthday cannot be empty");
        }

        var newPerson = new Person();
        newPerson.setFirstName(firstName);
        newPerson.setLastName(lastName);
        newPerson.setBirthday(birthday);
        personDao.save(newPerson);

        return newPerson;
    }
}
