package pl.umk.mat.planner.subject;

import pl.umk.mat.planner.event.Event;
import pl.umk.mat.planner.lecturer.Lecturer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private pl.umk.mat.planner.group.Group group;

    @OneToMany(mappedBy = "subject")
    private List<Event> events = new java.util.ArrayList<>();

    public Subject() {
    }

    public Subject(String name, Lecturer lecturer, pl.umk.mat.planner.group.Group group, List<Event> events) {
        this.name = name;
        this.lecturer = lecturer;
        this.group = group;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public pl.umk.mat.planner.group.Group getGroup() {
        return group;
    }

    public void setGroup(pl.umk.mat.planner.group.Group group) {
        this.group = group;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}