package pl.umk.mat.planner.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.connector.subject.year = ?1")
    Set<Event> findByConnector_Subject_Year(yearType year);

    @Override
    Optional<Event> findById(Long aLong);

//    Set<Event> findByDateFromIsBetweenAndDateToIsBetweenAndConnector_Subject_YearIs(OffsetDateTime dateFromStart, OffsetDateTime dateFromEnd, OffsetDateTime dateToStart, OffsetDateTime dateToEnd, yearType year);


}