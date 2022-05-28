package pl.umk.mat.planner.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.planner.PlannerApplication;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.connector.ConnectorRepository;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.room.RoomRepository;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventRepository repository;
    private final ConnectorRepository connectorRepository;
    private final RoomRepository roomRepository;

    private final EntityManager em;

    public EventController(EventRepository repository, ConnectorRepository connectorRepository, RoomRepository roomRepository, EntityManager em) {
        this.connectorRepository = connectorRepository;
        this.roomRepository = roomRepository;
        this.em = em;
        Assert.notNull(repository, "We need a repository!");
        this.repository = repository;
    }

    @GetMapping("/event")
    List<Event> findAll() {
        return repository.findAll();
    }

    @SneakyThrows
    @GetMapping(name = "api_event_getallbetween", path = "/event/filtered")
    List<Event> findAllBetween(@RequestParam String start, @RequestParam String end, @RequestParam String group_type, @RequestParam String group_number, @RequestParam String year_type) {
        Logger log = LoggerFactory.getLogger(PlannerApplication.class);
        log.info(start);
        log.info(end);
        OffsetDateTime offset1 = OffsetDateTime.parse(start.concat("+02:00"));
        OffsetDateTime offset2 = OffsetDateTime.parse(end.concat("+02:00"));

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Event> cq = cb.createQuery(Event.class);
        Root<Event> eventRoot = cq.from(Event.class);

        Predicate startPredicament = cb.between(eventRoot.get("dateFrom"), offset1, offset2);
        Predicate endPredicament = cb.between(eventRoot.get("dateTo"), offset1, offset2);
        cq.where(startPredicament, endPredicament);
//
        log.info(group_type);
        groupType gt = null;

        switch (group_type) {
            case "WYKLAD" -> gt = groupType.WYKLAD;
            case "LAB" -> gt = groupType.LAB;
            case "NIEST" -> gt = groupType.NIEST;
        }

        if (gt != null) {
            Predicate groupTypePredicate = cb.equal(eventRoot.get("connector").get("group").get("type"), gt);
            cq.where(groupTypePredicate);
        }
//
//
        String gn = group_number.equals("all") ? "Wszyscy" : "Grupa ".concat(group_number);
        log.info(gn);
        Predicate groupNumberPredicate = cb.equal(eventRoot.get("connector").get("group").get("name"), gn);
        cq.where(groupNumberPredicate);
//
        yearType yt = null;
        switch (year_type) {
            case "1" -> yt = yearType.ROK_I;
            case "2" -> yt = yearType.ROK_II;
            case "3" -> yt = yearType.ROK_III;
            case "4" -> yt = yearType.ROK_IV;
        }
        if (yt != null) {
            Predicate yearTypePredicate = cb.equal(eventRoot.get("connector").get("group").get("yearType"), yt);
            cq.where(yearTypePredicate);
        }



        TypedQuery<Event> query = em.createQuery(cq);
//        System.out.println(query.unwrap(Query.class).getQ);
        return query.getResultList();
//                .stream()
//                .map(q -> {
//                    String title = ""
//                            .concat(q.getConnector().getLecturer().getFullName())
//                            .concat(" - ")
//                            .concat(q.getConnector().getSubject().getName())
//                            .concat(" pokój ")
//                            .concat(q.getConnector().getRoom().getNumber());
//                    return new EventDto(q.getId(), q.getDateFrom(), q.getDateTo(), title);
//                });
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

        if (connector.isPresent() && room.isPresent()) {
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

    @SneakyThrows
    @PutMapping("/event/{id}")
    public void replace(@RequestBody String newEvent, @PathVariable Long id) {
        System.out.println(newEvent);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode node = objectMapper.readTree(newEvent);
        OffsetDateTime from = OffsetDateTime.parse(node.get("dateFrom").asText());
        OffsetDateTime to = OffsetDateTime.parse(node.get("dateTo").asText());
        Optional<Connector> connector = connectorRepository.findById(node.get("connector").asLong());
        Optional<Room> room = roomRepository.findById(node.get("room").asLong());
        System.out.println(connector.get());
        System.out.println(room.get());
        if (connector.isPresent() && room.isPresent()) {
            repository.findById(id)
                    .map(event -> {
                        event.setDateFrom(from);
                        event.setDateTo(to);
                        event.setConnector(connector.get());
                        event.setRoom(room.get());
                        return repository.save(event);
                    })
                    .orElseThrow(EntityNotFoundException::new);
        }
    }

    @DeleteMapping("/event/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
