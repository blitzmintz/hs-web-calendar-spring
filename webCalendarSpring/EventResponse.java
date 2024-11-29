package webCalendarSpring;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EventResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @NotEmpty
    private String event;
    @NotEmpty
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private Long id;
    public EventResponse(){}
    public EventResponse(String event, LocalDate date) {
        this.event = event;
        this.date = date;

    }
    public EventResponse(String message, String event, LocalDate date) {
        this.message = message;
        this.event = event;
        this.date = date;

    }
    public EventResponse(Long id, String event, LocalDate date) {
        this.id = id;
        this.event = event;
        this.date = date;

    }
    public EventResponse(Long id, String message, String event, LocalDate date) {
        this.id = id;
        this.message = message;
        this.event = event;
        this.date = date;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
