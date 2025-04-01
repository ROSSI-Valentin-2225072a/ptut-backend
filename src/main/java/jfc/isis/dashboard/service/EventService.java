package jfc.isis.dashboard.service;

import jakarta.transaction.Transactional;
import jfc.isis.dashboard.dao.EventRepository;
import jfc.isis.dashboard.entity.Event;
import jfc.isis.dashboard.entity.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventDao;

    public EventService(EventRepository eventRepository) {
        this.eventDao = eventRepository;
    }

    @Transactional
    public void deleteEventById(Integer eventId) {
        eventDao.deleteById(eventId);
    }

    @Transactional
    public ResponseEntity<?> getEventById(Integer eventId) {
        return ResponseEntity.ok(eventDao.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found")));
    }

    @Transactional
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    @Transactional
    public Event saveEvent(String nomEvent, String description, List<Type> types, Date dateEvent) {
        var newEvent = new Event();
        newEvent.setNomEvent(nomEvent);
        newEvent.setDateEvent(dateEvent);
        newEvent.setDescription(description);
        newEvent.setType(types);

        return eventDao.save(newEvent);
    }

    @Transactional
    public Event updateEvent(Integer eventId,
                             Optional<String> nomEvent,
                             Optional<String> description,
                             Optional<List<Type>> types,
                             Optional<Date> dateEvent) {
        var eventToUpdate = eventDao.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        nomEvent.ifPresent(eventToUpdate::setNomEvent);
        dateEvent.ifPresent(eventToUpdate::setDateEvent);
        description.ifPresent(eventToUpdate::setDescription);
        types.ifPresent(eventToUpdate::setType);

        return eventDao.save(eventToUpdate);
    }

}
