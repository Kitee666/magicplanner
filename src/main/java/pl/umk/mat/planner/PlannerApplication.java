package pl.umk.mat.planner;

import org.hibernate.type.OffsetDateTimeType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.event.Event;
import pl.umk.mat.planner.group.Group;
import pl.umk.mat.planner.group.GroupRepository;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.subject.Subject;
import pl.umk.mat.planner.subject.SubjectRepository;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@SpringBootApplication
public class PlannerApplication {


    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(LecturerRepository lecturerRepository, GroupRepository groupRepository){
//        return args -> {
//            // TODO: transactions
//            Lecturer janek = new Lecturer("Jan","Kowal","Bardzociekway");
//            lecturerRepository.save(janek);
//        };
//    }
}
