package com.example.demo.Controller;
import com.example.demo.model.Event;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping("/events")

public class EventController {
    private  List<Event> events=  new ArrayList<>();
    @GetMapping
    public List<Event> getAllEvents() {
        return events;
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }

    @PostMapping
    public String addEvent(@RequestBody Event event){
        events.add(event);
        return "Event added successfully";
    }

    @DeleteMapping
    public String deleteEvent(@RequestBody Event event){
        events.remove(event);
        return "Event deleted successfully";
    }
}