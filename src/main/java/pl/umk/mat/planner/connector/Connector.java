package pl.umk.mat.planner.connector;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Entity
@Table(name = "connector")
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonManagedReference
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lecturer_id", nullable = false)
    @JsonManagedReference
    private Lecturer lecturer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    @JsonManagedReference
    private Group group;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private Room room;

    @JsonBackReference
    @OneToMany(mappedBy = "connector", orphanRemoval = true)
    @JsonBackReference
    private Set<Event> events = new LinkedHashSet<>();

    public Connector(Subject subject, Lecturer lecturer, Group group, Room room) {
        this.subject = subject;
        this.lecturer = lecturer;
        this.group = group;
        this.room = room;
    }
}