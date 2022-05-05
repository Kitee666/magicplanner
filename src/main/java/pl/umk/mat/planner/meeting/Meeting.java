package pl.umk.mat.planner.meeting;

import pl.umk.mat.planner.event.Event;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private OffsetDateTime dateFrom;

    @Column(name = "date_to")
    private OffsetDateTime dateTo;

    @OneToMany(mappedBy = "meeting")
    private List<Event> events = new java.util.ArrayList<>();

    public Meeting() {
    }

    public Meeting(OffsetDateTime dateFrom, OffsetDateTime dateTo, List<Event> events) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(OffsetDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public OffsetDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(OffsetDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}