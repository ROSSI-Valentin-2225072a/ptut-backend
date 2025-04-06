package jfc.isis.dashboard.controller;

import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.PersonDTO;
import jfc.isis.dashboard.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard/person")
public class PersonController {

    private final PersonService personService;

    private final ModelMapper mapper;

    public PersonController(PersonService personService, ModelMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody PersonDTO newPerson) {
        try {
            var dashboard = personService.savePerson(newPerson.getFirstName(), newPerson.getLastName(), newPerson.getBirthday());
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("id={personId}")
    public ResponseEntity<?> getPersonById(
            @RequestParam Integer personId) {
        try {
            var dashboard = personService.findByPersonId(personId);
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/firstName={firstName}")
    public ResponseEntity<?> getPersonByFirstName(
            @RequestParam String firstName) {
        try {
            var dashboard = personService.findByFirstName(firstName);
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/lastName={lastName}")
    public ResponseEntity<?> getPersonByLastName(
            @RequestParam String lastName) {
        try {
            var dashboard = personService.findByLastName(lastName);
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/birthday={birthday}")
    public ResponseEntity<?> getPersonByBirthday(
            @RequestParam Date birthday) {
        try {
            var dashboard = personService.findByBirthday(birthday);
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPersons() {
        try {
            var dashboard = personService.findAllPersons();
            var body = dashboard.stream()
                    .map(d -> mapper.map(d, PersonDTO.class))
                    .toList();
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @DeleteMapping("id={personId}")
    public ResponseEntity<?> deletePersonById(
            @RequestParam Integer personId) {
        try {
            personService.deletePersonById(personId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @PutMapping("id={personId}")
    public ResponseEntity<?> updatePerson(
            @RequestBody PersonDTO updatedPerson) {
        try {
            var dashboard = personService.updatePerson(updatedPerson.getPersonId(),
                    Optional.of(updatedPerson.getFirstName()),
                    Optional.of(updatedPerson.getLastName()),
                    Optional.of(updatedPerson.getBirthday()));
            var body = mapper.map(dashboard, PersonDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

}
