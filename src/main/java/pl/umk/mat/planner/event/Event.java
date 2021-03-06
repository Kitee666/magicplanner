package pl.umk.mat.planner.event;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.meeting.Meeting;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.subject.Subject;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private OffsetDateTime dateFrom;

    @Column(name = "date_to", nullable = false)
    private OffsetDateTime dateTo;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "connector_id", nullable = false)
    private Connector connector;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Event(OffsetDateTime dateFrom, OffsetDateTime dateTo, Connector connector, Room room) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.connector = connector;
        this.room = room;
    }
}