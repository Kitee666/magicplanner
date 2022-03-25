package pl.umk.mat.planner.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.meeting.Meeting;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.subject.Subject;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date_from", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dateFrom;

    @Column(name = "date_to", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime dateTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    public Event() {
    }

    public Event(OffsetDateTime dateFrom, OffsetDateTime dateTo, Subject subject, Room room, Meeting meeting) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.subject = subject;
        this.room = room;
        this.meeting = meeting;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}