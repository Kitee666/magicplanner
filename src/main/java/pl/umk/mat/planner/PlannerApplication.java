package pl.umk.mat.planner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.umk.mat.planner.group.Group;
import pl.umk.mat.planner.group.GroupRepository;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;


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
