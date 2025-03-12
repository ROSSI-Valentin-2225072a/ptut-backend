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

}
