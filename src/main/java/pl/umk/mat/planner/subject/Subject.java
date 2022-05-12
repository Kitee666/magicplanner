package pl.umk.mat.planner.subject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

    @ManyToOne
    @JoinColumn(name = "connector_id")
    private Connector connector;


    @JsonBackReference
    @OneToMany(mappedBy = "subject", orphanRemoval = true)
    private Collection<Connector> connectors = new ArrayList<>();

    public Subject(String name, yearType year, Integer hours) {
        this.name = name;
        this.year = year;
        this.hours = hours;
    }
}