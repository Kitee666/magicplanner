package pl.umk.mat.planner.room;

import pl.umk.mat.planner.event.Event;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "number", nullable = false)
    private String number;

    @OneToMany(mappedBy = "room")
    private List<Event> events = new java.util.ArrayList<>();

    public Room() {
    }

    public Room(String number, List<Event> events) {
        this.number = number;
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}