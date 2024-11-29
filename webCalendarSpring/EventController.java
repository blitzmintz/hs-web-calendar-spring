package webCalendarSpring;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class EventController {
    @Autowired
    private RepositoryInterface repositoryInterface;
    private final List<String> todayList = Collections.emptyList();

    @DeleteMapping("event/{id}")
    public ResponseEntity<?>  deleteEvent(@PathVariable("id") Long id) {
        Optional<Event> optionalEvent = repositoryInterface.findById(id);
        if (!optionalEvent.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "The event doesn't exist!"));

        }
        Event event = optionalEvent.get();
        EventResponse eventResponse = new EventResponse(event.getId(), event.getEvent(), event.getEventDate());
        repositoryInterface.deleteById(id);
        return ResponseEntity.ok(eventResponse);
    }


    @PostMapping("/event")
    public ResponseEntity<Object> postEvent(@Valid @RequestBody EventRequest eventRequest) {

        Event event = new Event();
        event.setEvent(eventRequest.getEvent());
        event.setEventDate(eventRequest.getDate());
        Event savedEvent = repositoryInterface.save(event);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "The event has been added!");
        response.put("event", event.getEvent());
        response.put("date", event.getEventDate());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/event/today")
    public ResponseEntity<List<EventResponse>> getTodaysEvents() {
        LocalDate currentDate = LocalDate.now();
        List<Event> todaysEvents = repositoryInterface.findByDate(currentDate);
        List<EventResponse> responses = todaysEvents.stream()
                .map(event -> new EventResponse(event.getId(), event.getEvent(), event.getEventDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);

    }

    @GetMapping("/event")
    public ResponseEntity<List<EventResponse>> getEventsList(@RequestParam(required = false) String start_time, @RequestParam(required = false) String end_time) {
        List<Event> events;
        if (start_time != null && end_time != null) {
            LocalDate startDate = LocalDate.parse(start_time);
            LocalDate endDate = LocalDate.parse(end_time);
            events = repositoryInterface.findByDateBetween(startDate, endDate);
        } else {
            events = repositoryInterface.findAll();
        }
        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EventResponse> responses = events.stream()
                .map(event -> new EventResponse(event.getId(), event.getEvent(), event.getEventDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);

    }

    @GetMapping(value = "/event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        Optional<Event> event = repositoryInterface.findById(id);
        if (event.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "The event doesn't exist!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
        Event eventRequested = event.get();
        EventResponse eventResponse = new EventResponse(eventRequested.getId(), eventRequested.getEvent(), eventRequested.getEventDate());
        return ResponseEntity.ok(eventResponse);
    }

}
