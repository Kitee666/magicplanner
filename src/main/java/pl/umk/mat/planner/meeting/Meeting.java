package pl.umk.mat.planner.meeting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.event.Event;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_from", nullable = false, unique = true)
    private OffsetDateTime dateFrom;

    @Column(name = "date_to", nullable = false, unique = true)
    private OffsetDateTime dateTo;

    public Meeting(OffsetDateTime dateFrom, OffsetDateTime dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}