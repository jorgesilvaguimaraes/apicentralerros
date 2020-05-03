package br.com.apicentralerros.api.model.output;

import br.com.apicentralerros.domain.entity.StatusLevel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventOutPutModel {

    private Integer id;
    private StatusLevel level;
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:ss")
    private LocalDateTime date;
    private Integer countEvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusLevel getLevel() {
        return level;
    }

    public void setLevel(StatusLevel level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCountEvent() {
        return countEvent;
    }

    public void setCountEvent(Integer countEvent) {
        this.countEvent = countEvent;
    }

}
