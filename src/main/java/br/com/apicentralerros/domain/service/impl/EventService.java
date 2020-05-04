package br.com.apicentralerros.domain.service.impl;

import br.com.apicentralerros.domain.entity.Event;
import br.com.apicentralerros.domain.repository.EventRepository;
import br.com.apicentralerros.domain.service.interfaces.EventInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventInterface {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> index(Optional<String> level, Optional<String> description, Optional<String> origin, Optional<LocalDate> date, Pageable pageable) {

        if(level.isPresent()){
            return eventRepository.findByLevel(level.get(), pageable).getContent();
        }

        if(description.isPresent()){
            return eventRepository.findByDescription(description.get(), pageable).getContent();
        }

        if(origin.isPresent()){
            return eventRepository.findByOrigin(origin.get(), pageable).getContent();
        }

        if(date.isPresent()){
            return eventRepository.findByDateBetween(date.get().atTime(LocalTime.MIN), date.get().atTime(LocalTime.MAX), pageable).getContent();
        }

        return eventRepository.findAll(pageable).getContent();
    }

    @Override
    public Event store(Event event) {
        event.setDate(LocalDateTime.now());
        event.setCountEvent(event.getLogs().size());
        return  this.eventRepository.save(event);
    }

    @Override
    public Optional<Event> show(Integer id) {
        return this.eventRepository.findById(id);
    }
}
