package com.theo.sprbandevents.controller;

import com.theo.sprbandevents.model.Band;
import com.theo.sprbandevents.model.Event;
import com.theo.sprbandevents.repository.BandRepository;
import com.theo.sprbandevents.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BandRepository bandRepository;

    @GetMapping
    public List<Event> listBands() {
        return eventRepository.findAll();
    }

    @PostMapping
    public Event registerBand(@RequestBody Event event) {
        Set<Band> bandsList = new HashSet<>();

        Event savedEvent = eventRepository.save(event);

        for(int bandId: event.getBandsIds()) {
            bandsList.add(bandRepository.getById(bandId));
            eventRepository.addBandEvent(bandId, event.getId());
        }

        savedEvent.setBands(bandsList);
        return savedEvent;
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        eventRepository.removeEventFromRelationship(eventId);
        eventRepository.removeEvent(eventId);
    }

    @PutMapping("/{eventId}")
    public void updateEvent(@RequestBody Event event, @PathVariable Integer eventId) {
        eventRepository.removeEventFromRelationship(eventId);
        event.setId(eventId);
        eventRepository.save(event);

        for(int bandId: event.getBandsIds()) {
            eventRepository.addBandEvent(bandId, event.getId());
        }
    }
}
