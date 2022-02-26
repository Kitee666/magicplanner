package pl.umk.mat.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }
    @GetMapping("/home")
    public String home(){
        return "Hello World?";
    }
}
