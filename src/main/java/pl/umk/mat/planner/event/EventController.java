package pl.umk.mat.planner.event;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/event")
    List<Event> findAll() {
        return repository.findAll();
    }

    @GetMapping("/event/{id}")
    Event findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @PostMapping("/event")
    Event add(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @PutMapping("/event/{id}")
    Event replace(@RequestBody Event newEvent, @PathVariable Long id) {
        return repository.findById(id)
                .map(event -> {
                    event.setDateFrom(newEvent.getDateFrom());
                    event.setDateTo(newEvent.getDateTo());
                    event.setSubject(newEvent.getSubject());
                    event.setRoom(newEvent.getRoom());
                    event.setMeeting(newEvent.getMeeting());
                    return repository.save(event);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/event/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
