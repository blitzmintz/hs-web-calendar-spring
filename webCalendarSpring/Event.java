package webCalendarSpring;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name = "event", nullable = false)
    @NotNull
    private String event;
    @Column(name = "date", nullable = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Event() {}

    public Event(@JsonProperty("event") String event, @JsonProperty("date") LocalDate date) {
        this.event = event;
        this.date = date;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public void setEventDate(LocalDate date) {
        this.date = date;
    }
    public String getEvent() {
        return event;
    }
    public LocalDate getEventDate() {
        return date;

    }

}
