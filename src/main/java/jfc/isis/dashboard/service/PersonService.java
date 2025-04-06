package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.PersonRepository;
import jfc.isis.dashboard.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Person findByPersonId(Integer personId) {
        return personDao.findByPersonId(personId);
    }

    @Transactional
    public Person findByFirstName(String firstName) {
        return personDao.findByFirstName(firstName);
    }

    @Transactional
    public Person findByLastName(String lastName) {
        return personDao.findByLastName(lastName);
    }

    @Transactional
    public Person findByBirthday(Date birthday) {
        return personDao.findByBirthday(birthday);
    }

    @Transactional
    public List<Person> findAllPersons() {
        return personDao.findAll();
    }

    @Transactional
    public void deletePersonById(Integer personId) {
        personDao.deleteById(personId);
    }

    @Transactional
    public Person updatePerson(Integer personId, Optional<String> firstName, Optional<String> lastName, Optional<Date> birthday) {
        var personToUpdate = personDao.findByPersonId(personId);
        firstName.ifPresent(personToUpdate::setFirstName);
        lastName.ifPresent(personToUpdate::setLastName);
        birthday.ifPresent(personToUpdate::setBirthday);
        personDao.save(personToUpdate);
        return personToUpdate;
    }
}
