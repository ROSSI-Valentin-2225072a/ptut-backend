package jfc.isis.dashboard.controller;

import jakarta.validation.constraints.Positive;
import jfc.isis.dashboard.DTO.ApiErrorDTO;
import jfc.isis.dashboard.DTO.EventDTO;
import jfc.isis.dashboard.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/dashboard/event")
public class EventController {

    private final EventService eventService;
    private final ModelMapper mapper;

    public EventController(EventService eventService, ModelMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> saveEvent(@RequestBody EventDTO newEvent) {
        try {
            var result = eventService.saveEvent(
                    newEvent.getNomEvent(),
                    newEvent.getDescription(),
                    newEvent.getType(),
                    newEvent.getDateEvent(),
                    newEvent.getLieu());
            var body = mapper.map(result, EventDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        try {
            var result = eventService.getAllEvents();
            var body = result.stream()
                    .map(d -> mapper.map(d, EventDTO.class))
                    .toList();
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("id={eventId}")
    public ResponseEntity<?> getEventById(
            @PathVariable @Positive Integer eventId) {
        try {
            var result = eventService.getEventById(eventId);
            var body = mapper.map(result, EventDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @DeleteMapping("id={eventId}")
    public ResponseEntity<?> deleteEventById(
            @PathVariable @Positive Integer eventId) {
        try {
            eventService.deleteEventById(eventId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }

    @PutMapping("id={eventId}")
    public ResponseEntity<?> updateEvent(
            @PathVariable @Positive Integer eventId,
            @RequestBody EventDTO updatedEvent) {
        try {
            var result = eventService.updateEvent(
                    eventId,
                    Optional.of(updatedEvent.getNomEvent()),
                    Optional.of(updatedEvent.getDescription()),
                    Optional.of(updatedEvent.getType()),
                    Optional.of(updatedEvent.getDateEvent()),
                    Optional.of(updatedEvent.getLieu()));
            var body = mapper.map(result, EventDTO.class);
            return ResponseEntity.ok(body);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("An error occurred: " + e.getMessage()));
        }
    }


}
