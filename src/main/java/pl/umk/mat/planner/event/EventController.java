package pl.umk.mat.planner.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.connector.ConnectorRepository;
import pl.umk.mat.planner.mappers.EventInfo;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.room.RoomRepository;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventRepository repository;
    private final ConnectorRepository connectorRepository;
    private final RoomRepository roomRepository;

    public EventController(EventRepository repository, ConnectorRepository connectorRepository, RoomRepository roomRepository) {
        this.connectorRepository = connectorRepository;
        this.roomRepository = roomRepository;
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

    @SneakyThrows
    @PostMapping("/event")
    public void add(@RequestBody String request) {
        System.out.println(request);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode node = objectMapper.readTree(request);
        System.out.println(node.get("dateFrom").asText());

        OffsetDateTime from = OffsetDateTime.parse(node.get("dateFrom").asText());
        OffsetDateTime to = OffsetDateTime.parse(node.get("dateTo").asText());
        Optional<Connector> connector = connectorRepository.findById(node.get("connector").asLong());
        Optional<Room> room = roomRepository.findById(node.get("room").asLong());

        if(connector.isPresent() && room.isPresent()) {
            Event event = new Event();
            event.setDateFrom(from);
            event.setDateTo(to);
            connector.ifPresent(event::setConnector);
            room.ifPresent(event::setRoom);
            System.out.println(event);
            repository.save(event);
        } else {
            throw new Exception("Nie znaleziono objektów z referencji!");
        }
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
