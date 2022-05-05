package pl.umk.mat.planner.meeting;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MeetingController {

    private final MeetingRepository repository;

    public MeetingController(MeetingRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/meeting")
    List<Meeting> findAll() {
        return repository.findAll();
    }

    @GetMapping("/meeting/{id}")
    Meeting findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/meeting")
    Meeting add(@RequestBody Meeting newMeeting) {
        return repository.save(newMeeting);
    }

    @PutMapping("/meeting/{id}")
    Meeting replace(@RequestBody Meeting newMeeting, @PathVariable Long id) {
        return repository.findById(id)
                .map(meeting -> {
                    meeting.setDateFrom(newMeeting.getDateFrom());
                    meeting.setDateTo(newMeeting.getDateTo());
                    meeting.setEvents(newMeeting.getEvents());
                    return repository.save(meeting);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping("/meeting/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
