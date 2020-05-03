package br.com.apicentralerros.domain.entity;


import br.com.apicentralerros.domain.validators.Level;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotNull
    @Level(enumClass = StatusLevel.class)
    private String level;

    @Column
    @NotNull
    private String description;


    @Column
    @NotNull
    private String origin;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    @Column
    @NotNull
    private Integer countEvent;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private Set<Log> logs;

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getCountEvent() {
        return countEvent;
    }

    public void setCountEvent(Integer countEvent) {
        this.countEvent = countEvent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    public void addLog(String log){


        Log logs = new Log();
        logs.setId(1);
        logs.setLog(log);
        logs.setEvent(this);

       Set<Log> logAray =  new HashSet<>();
        logAray.add(logs);

        setLogs(logAray);
    }
}
