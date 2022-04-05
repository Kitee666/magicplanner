package pl.umk.mat.planner.lecturer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LecturerController {

    private final LecturerRepository repository;

    public LecturerController(LecturerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/lecturers")
    List<Lecturer> all() {
        return repository.findAll();
    }
}
