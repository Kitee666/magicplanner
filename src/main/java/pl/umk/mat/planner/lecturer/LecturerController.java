package pl.umk.mat.planner.lecturer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LecturerController {

    private final LecturerRepository repository;

    public LecturerController(LecturerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/lecturer")
    List<Lecturer> findAll() {
        return repository.findAll();
    }

    @GetMapping("/lecturer/{id}")
    Lecturer findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(path = "/lecturer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Lecturer add(@RequestBody Lecturer newLecturer) {
        return repository.save(newLecturer);
    }

    @PutMapping("/lecturer/{id}")
    Lecturer replace(@RequestBody Lecturer newLecturer, @PathVariable Long id) {
        return repository.findById(id)
                .map(lecturer -> {
                    lecturer.setName(newLecturer.getName());
                    lecturer.setLastname(newLecturer.getLastname());
                    lecturer.setTitle(newLecturer.getTitle());
                    return repository.save(lecturer);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/lecturer/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
