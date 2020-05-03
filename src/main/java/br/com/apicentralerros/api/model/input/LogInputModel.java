package br.com.apicentralerros.api.model.input;

import javax.validation.constraints.NotBlank;

public class LogInputModel {

    @NotBlank
    private String log;

    private EventLogInputModel event;

    public EventLogInputModel getEvent() {
        return event;
    }

    public void setEvent(EventLogInputModel event) {
        this.event = event;
    }

    public LogInputModel() {
    }

    public LogInputModel(@NotBlank String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
