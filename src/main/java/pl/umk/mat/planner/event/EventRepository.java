package pl.umk.mat.planner.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    static List<Event> findAllBetween() {
        return null;
    };
}