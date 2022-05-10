package pl.umk.mat.planner.meeting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate dateFrom;

    @Column(name = "date_to", nullable = false, unique = true)
    private LocalDate dateTo;

    public Meeting(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}