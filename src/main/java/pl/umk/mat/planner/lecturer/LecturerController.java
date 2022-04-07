package pl.umk.mat.planner.lecturer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LecturerController {

    private final LecturerRepository repository;

    public LecturerController(LecturerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/lecturer")
    List<Lecturer> all() {
        return repository.findAll();
    }

    @GetMapping("/lecturer/{id}")
    Lecturer one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LecturerNotFoundException(id));
    }

    @PostMapping("/lecturer")
    Lecturer newLecturer(@RequestBody Lecturer newLecturer) {
        return repository.save(newLecturer);
    }

    @PutMapping("/lecturer/{id}")
    Lecturer replaceLecturer(@RequestBody Lecturer newLecturer, @PathVariable Long id) {
        return repository.findById(id)
                .map(lecturer -> {
                    lecturer.setName(newLecturer.getName());
                    lecturer.setLastname(newLecturer.getLastname());
                    lecturer.setSubject(newLecturer.getSubject());
                    return repository.save(lecturer);
                })
                .orElseThrow(() -> new LecturerNotFoundException(id));
    }

}
