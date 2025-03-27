package jfc.isis.dashboard.controller;

import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.PersonDTO;
import jfc.isis.dashboard.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/dashboard/person")
public class PersonController {

    private final PersonService personService;

    private final ModelMapper mapper;

    public PersonController(PersonService personService, ModelMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> savePerson(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Date birthday) {
        try {
            var dashboard = personService.savePerson(firstName, lastName, birthday);
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

}
