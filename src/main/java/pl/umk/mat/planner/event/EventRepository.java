package pl.umk.mat.planner.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import pl.umk.mat.planner.mappers.EventInfo;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
//    @Query("SELECT e FROM Event e left join fetch e.connector")
//    List<EventInfo> findBetween();

//    @Query("""
//            select e from Event e
//            left join e.connector c
//            where e.dateFrom between :dateStart and :dateEnd or e.dateTo between :dateStart and :dateEnd""")
//    List<EventInfo> findByDateBetween(@Param("dateFromStart") @NonNull OffsetDateTime dateStart, @Param("dateFromEnd") @NonNull OffsetDateTime dateEnd);
    @Query("""
            select e from Event e
            left join e.connector c
            """)
    List<EventInfo> findByDateBetween();
}