package br.com.apicentralerros.domain.repository;

import br.com.apicentralerros.domain.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {
    Optional<Event> findById(Integer id);

    Page<Event> findByLevel(String level, Pageable pageable);

    Page<Event> findByDescription(String description, Pageable pageable);

    Page<Event> findByOrigin(String origin, Pageable pageable);

    Page<Event> findByDateBetween(LocalDateTime start_date, LocalDateTime end_date, Pageable pageable);

//    Page<Event> findByDate(LocalDateTime date, Pageable pageable);

    Page<Event> findAll(Pageable pageable);
}
