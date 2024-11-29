package webCalendarSpring;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class EventRequest {
    @NotEmpty
    @NotBlank
    @NotNull
    private String event;
    @NotNull
    private LocalDate date;
    public EventRequest() {}
    public EventRequest(String event, LocalDate date) {
        this.event = event;
        this.date = date;
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
}
