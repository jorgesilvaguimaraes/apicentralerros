package br.com.apicentralerros.api.model.input;

import br.com.apicentralerros.domain.entity.StatusLevel;
import br.com.apicentralerros.domain.validators.Level;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EventLogInputModel {

    private Integer id;

    @NotNull
    @Level(enumClass = StatusLevel.class)
    private String level;

    @NotBlank
    private String description;

    @NotBlank
    private String origin;

    @NotNull
    private Integer countEvent;

    @Valid
    @NotNull
    private List<LogInputModel> logs;

    public EventLogInputModel() {
        logs = new ArrayList<>();
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

    public List<LogInputModel> getLogs() {
        return logs;
    }

    public void setLogs(List<LogInputModel> logs) {
        this.logs = logs;
    }


}
