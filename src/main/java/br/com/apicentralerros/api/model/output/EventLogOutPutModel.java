package br.com.apicentralerros.api.model.output;

import br.com.apicentralerros.domain.entity.StatusLevel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;


public class EventLogOutPutModel {

    private Integer id;
    private StatusLevel level;
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:ss")
    private LocalDateTime date;
    private Integer countEvent;
    private Set<LogOutPutModel> logs ;

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

    public Set<LogOutPutModel> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogOutPutModel> logs) {
        this.logs = logs;
    }
}
