package br.com.apicentralerros.domain.service.interfaces;

import br.com.apicentralerros.domain.entity.Event;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventInterface extends ServiceInterface<Event> {

    List<Event> index(Optional<String> level, Optional<String> description, Optional<String> origin, Optional<LocalDate> date, Pageable pageable);

    Event store(Event event);

    Optional<Event> show(Integer id);

}
