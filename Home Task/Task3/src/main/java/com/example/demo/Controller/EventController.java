package com.example.demo.Controller;
import com.example.demo.model.Event;
import org.springframework.web.bind.annotation.*;
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