package pl.umk.mat.planner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.lecturer.LecturerRepository;

@SpringBootApplication
@RestController
public class PlannerApplication {

    private static final Logger log = LoggerFactory.getLogger(PlannerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }

    @GetMapping("/home")
    public String home(LecturerRepository repository){
        //TODO fix hibernate error
        try {
            Lecturer lecturer;
            lecturer = repository.findById(33).get();
            lecturer.setName("Jeff");
            repository.save(lecturer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello World?";
    }

    @Bean
    public int demo(LecturerRepository repository) {
        try {
            Lecturer lecturer;
            lecturer = repository.findById(33).get();
            lecturer.setName("Jeff");
            repository.save(lecturer);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }
}
