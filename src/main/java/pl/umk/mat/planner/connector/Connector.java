package pl.umk.mat.planner.connector;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.umk.mat.planner.event.Event;
import pl.umk.mat.planner.group.Group;
import pl.umk.mat.planner.lecturer.Lecturer;
import pl.umk.mat.planner.room.Room;
import pl.umk.mat.planner.subject.Subject;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "connector")
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "connector", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Subject> subjects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "connector", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Lecturer> lecturers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "connector", orphanRemoval = true)
    private Set<Group> groups = new LinkedHashSet<>();

    @OneToMany(mappedBy = "connector", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<Event> events = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id")
    private Room room;

}