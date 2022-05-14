package pl.umk.mat.planner.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private groupType type;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(name = "year_type", nullable = false)
    private yearType yearType;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group", orphanRemoval = true)
    @JsonBackReference
    private Collection<Connector> connectors = new java.util.ArrayList<>();

    public Group(groupType type, Integer size, pl.umk.mat.planner.types.yearType yearType, Integer hours, String name) {
        this.type = type;
        this.size = size;
        this.yearType = yearType;
        this.hours = hours;
        this.name = name;
    }
}