package pl.umk.mat.planner.connector;

import org.springframework.web.bind.annotation.*;
import pl.umk.mat.planner.event.Event;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ConnectorController {

    private final ConnectorRepository repository;

    public ConnectorController(ConnectorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/connector")
    List<Connector> findAll() {
        return repository.findAll();
    }

    @GetMapping("/connector/{id}")
    Connector findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    @PostMapping("/connector")
    Connector add(@RequestBody Connector newConnector) {
        return repository.save(newConnector);
    }

    @PutMapping("/connector/{id}")
    Connector replace(@RequestBody Connector newConnector, @PathVariable Long id) {
        return repository.findById(id)
                .map(connector -> {
                    connector.setSubject(newConnector.getSubject());
                    connector.setLecturer(newConnector.getLecturer());
                    connector.setGroup(newConnector.getGroup());
                    connector.setRoom(newConnector.getRoom());
                    return repository.save(connector);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/connector/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
