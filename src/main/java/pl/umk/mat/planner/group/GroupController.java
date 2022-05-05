package pl.umk.mat.planner.group;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GroupController {

    private final GroupRepository repository;

    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/group")
    List<Group> findAll() {
        return repository.findAll();
    }

    @GetMapping("/group/{id}")
    Group findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/group")
    Group add(@RequestBody Group newGroup) {
        return repository.save(newGroup);
    }

    @PutMapping("/group/{id}")
    Group replace(@RequestBody Group newGroup, @PathVariable Long id) {
        return repository.findById(id)
                .map(group -> {
                    group.setType(newGroup.getType());
                    group.setRok(newGroup.getRok());
                    group.setSize(newGroup.getSize());
                    group.setSubjects(newGroup.getSubjects());
                    return repository.save(group);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/group/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
