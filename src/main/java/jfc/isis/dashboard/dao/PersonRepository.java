package jfc.isis.dashboard.dao;

import jfc.isis.dashboard.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("""
        SELECT p.personId, p.firstName, p.lastName, p.birthday
        FROM Person p
        WHERE p.personId = :personId
    """)
    Person findByPersonId(int personId);

    @Query("""
        SELECT p.personId, p.firstName, p.lastName, p.birthday
        FROM Person p
        WHERE p.firstName = :firstName
    """)
    Person findByFirstName(String firstName);

    @Query("""
        SELECT p.personId, p.firstName, p.lastName, p.birthday
        FROM Person p
        WHERE p.lastName = :lastName
    """)
    Person findByLastName(String lastName);

    @Query("""
        SELECT p.personId, p.firstName, p.lastName, p.birthday
        FROM Person p
        WHERE p.birthday = :birthday
    """)
    Person findByBirthday(Date birthday);

}
