package pl.umk.mat.planner.group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.connector.Connector;
import pl.umk.mat.planner.types.groupType;
import pl.umk.mat.planner.types.yearType;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private Collection<Connector> connectors = new java.util.ArrayList<>();

}