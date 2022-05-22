package pl.umk.mat.planner.connector;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import pl.umk.mat.planner.group.Group;
import pl.umk.mat.planner.group.GroupRepository;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.room.RoomRepository;
import pl.umk.mat.planner.subject.Subject;
import pl.umk.mat.planner.subject.SubjectRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ConnectorController {

    private final ConnectorRepository connectorRepository;
    private final SubjectRepository subjectRepository;
    private final LecturerRepository lecturerRepository;
    private final GroupRepository groupRepository;
    private final RoomRepository roomRepository;



    public ConnectorController(ConnectorRepository connectorRepository, SubjectRepository subjectRepository, LecturerRepository lecturerRepository, GroupRepository groupRepository, RoomRepository roomRepository, ObjectMapper mapper) {
        this.connectorRepository = connectorRepository;
        this.subjectRepository = subjectRepository;
        this.lecturerRepository = lecturerRepository;
        this.groupRepository = groupRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/connector")
    List<Connector> findAll() {
        return connectorRepository.findAll();
    }

    @GetMapping("/connector/{id}")
    Connector findOne(@PathVariable Long id) {
        return connectorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @PostMapping("/connector")
    Connector add(@RequestBody Connector newConnector) {
        return connectorRepository.save(newConnector);
    }

    @SneakyThrows
    @PostMapping(value = "/connector/bulk", consumes = "application/json")
    @ResponseBody
    @Transactional
    public void bulk(@RequestBody String request) {
//        Get data from request
        System.out.println(request);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode node = objectMapper.readTree(request);

        Connector connector = new Connector();

        Subject subject = objectMapper.convertValue(node.get("subject"), Subject.class);
//        System.out.println(node);
        subjectRepository.save(subject);
        connector.setSubject(subject);

        for (Object gr : node.get("groups")) {
//            System.out.println(gr);
            Group group = objectMapper.convertValue(gr,Group.class);
//            System.out.println(group);
            connector.setGroup(group);
            groupRepository.save(group);
        }
        System.out.println(connector.getGroup());

        Optional<Lecturer> lecturer = lecturerRepository.findById(node.get("lecturer").asLong());
//        System.out.println(lecturer);
        Optional<Room> room = roomRepository.findById(node.get("room").asLong());
//        System.out.println(room);

        lecturer.ifPresent(connector::setLecturer);
        room.ifPresent(connector::setRoom);

//        System.out.println(connector.toString());
        connectorRepository.save(connector);

//        Save to connectorRepository
//        subjectRepository.save(subject);
    }

//    @PutMapping("/connector/{id}")
//    Connector replace(@RequestBody Connector newConnector, @PathVariable Long id) {
//        return connectorRepository.findById(id)
//                .map(connector -> {
//                    connector.setSubjects(newConnector.getSubject());
//                    connector.setLecturer(newConnector.getLecturer());
//                    connector.setGroup(newConnector.getGroup());
//                    connector.setRoom(newConnector.getRoom());
//                    return connectorRepository.save(connector);
//                })
//                .orElseThrow(EntityNotFoundException::new);
//    }

    @DeleteMapping("/connector/{id}")
    void delete(@PathVariable Long id) {
        connectorRepository.deleteById(id);
    }
}
