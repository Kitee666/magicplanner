package pl.umk.mat.planner.subject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Enumerated(EnumType.STRING)
    @Column(name = "year", nullable = false)
    private yearType year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "connector_id")
    @JsonIgnore
    private Connector connector;

    public Subject(String name, yearType year, Integer hours) {
        this.name = name;
        this.year = year;
        this.hours = hours;
    }
}