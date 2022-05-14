package pl.umk.mat.planner.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.connector.ConnectorRepository;
import pl.umk.mat.planner.event.Event;
import pl.umk.mat.planner.event.EventRepository;
import pl.umk.mat.planner.group.Group;
import pl.umk.mat.planner.group.GroupRepository;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.room.RoomRepository;
import pl.umk.mat.planner.subject.Subject;
import pl.umk.mat.planner.subject.SubjectRepository;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import java.time.OffsetDateTime;

///TO JEST PRZYKŁADOWY CONTROLLER DLA HOMEPAGE, PODRZUCĘ JESZCZE TEMPLATKE
@Controller
public class HomepageController {
    private SubjectRepository subjectRepository;
    private LecturerRepository lecturerRepository;
    private GroupRepository groupRepository;
    private RoomRepository roomRepository;
    private ConnectorRepository connectorRepository;
    private EventRepository eventRepository;

    public HomepageController(SubjectRepository subjectRepository, LecturerRepository lecturerRepository, GroupRepository groupRepository, RoomRepository roomRepository, ConnectorRepository connectorRepository, EventRepository eventRepository) {
        this.subjectRepository = subjectRepository;
        this.lecturerRepository = lecturerRepository;
        this.groupRepository = groupRepository;
        this.roomRepository = roomRepository;
        this.connectorRepository = connectorRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/")
    public String homePage() {

        Subject subject = new Subject("subject 1", yearType.ROK_I, 60);
        Lecturer lecturer = new Lecturer("Imie","Nazwisko", "mgr. inż.");
        Group group = new Group(groupType.WYKLAD,30,yearType.ROK_I,60,"gr");
        Room room = new Room("6767",16);
        Connector connector = new Connector(subject,lecturer,group,room);
        OffsetDateTime offset1 = OffsetDateTime.parse("2022-05-10T15:00:00+02:00");
        OffsetDateTime offset2 = OffsetDateTime.parse("2022-05-10T16:00:00+02:00");
        Event event = new Event(offset1,offset2,connector,room);

        this.subjectRepository.save(subject);
        this.lecturerRepository.save(lecturer);
        this.groupRepository.save(group);
        this.roomRepository.save(room);
        this.connectorRepository.save(connector);
        this.eventRepository.save(event);

        return "index";
    }
}
