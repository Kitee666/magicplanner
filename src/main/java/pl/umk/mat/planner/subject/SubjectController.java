package pl.umk.mat.planner.subject;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {

    private final SubjectRepository repository;

    public SubjectController(SubjectRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/subject")
    List<Subject> findAll() {
        return repository.findAll();
    }

    @GetMapping("/subject/{id}")
    Subject findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/subject")
    Subject add(@RequestBody Subject newSubject) {
        return repository.save(newSubject);
    }

    @PutMapping("/subject/{id}")
    Subject replace(@RequestBody Subject newSubject, @PathVariable Long id) {
        return repository.findById(id)
                .map(subject -> {
                    subject.setName(newSubject.getName());
                    subject.setLecturer(newSubject.getLecturer());
                    subject.setGroup(newSubject.getGroup());
                    subject.setEvents(newSubject.getEvents());
                    return repository.save(subject);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/subject/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
