package pl.umk.mat.planner.room;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoomController {

    private final RoomRepository repository;

    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/room")
    List<Room> findAll() {
        return repository.findAll();
    }

    @GetMapping("/room/{id}")
    Room findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/room")
    Room add(@RequestBody Room newRoom) {
        return repository.save(newRoom);
    }

    @PutMapping("/room/{id}")
    Room replace(@RequestBody Room newRoom, @PathVariable Long id) {
        return repository.findById(id)
                .map(room -> {
                    room.setNumber(newRoom.getNumber());
                    room.setSize(newRoom.getSize());
                    return repository.save(room);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/room/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}