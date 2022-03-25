package pl.umk.mat.planner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;

@SpringBootApplication
public class PlannerApplication {

    private static final Logger log = LoggerFactory.getLogger(PlannerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(LecturerRepository lecturerRepository){
        return args -> {
            Lecturer janek = new Lecturer("Jan","Kowal","Bardzociekway");
            lecturerRepository.save(janek);
        };
    }
}
