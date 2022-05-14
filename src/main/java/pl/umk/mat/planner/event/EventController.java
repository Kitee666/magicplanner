package pl.umk.mat.planner.event;

import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.planner.mappers.EventInfo;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventRepository repository;

    public EventController(EventRepository repository) {
        Assert.notNull(repository, "We need a repository!");
        this.repository = repository;
    }

    @GetMapping("/event")
    List<Event> findAll() {
        return repository.findAll();
    }

    @SneakyThrows
    @GetMapping(name = "api_event_getallbetween", path = "/event/filtered")
    List<EventInfo> findAllBetween() {
//        return repository.findByConnector_Group_HoursEquals(60);
        OffsetDateTime offset1 = OffsetDateTime.parse("2022-05-13T00:00:00+02:00");
        OffsetDateTime offset2 = OffsetDateTime.parse("2022-05-14T00:00:00+02:00");
//        CallendarEventType obj = new CallendarEventType();
//        obj.setId(1L);
//        obj.setStart(offset1);
//        obj.setEnd(offset2);
//        obj.setTitle("title 1");
//        List<CallendarEventType> callendarEventArrayList = new ArrayList<>();
//        callendarEventArrayList.add(obj);

        return repository.findByDateBetween();
//        return callendarEventArrayList;
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
//                    event.setConnector(newEvent.getConnector());
                    event.setRoom(newEvent.getRoom());
                    return repository.save(event);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/event/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
