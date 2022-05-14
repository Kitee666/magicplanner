package pl.umk.mat.planner.room;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.event.Event;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "size", nullable = false)
    private Integer size;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    @JsonBackReference
    private Set<Event> events = new LinkedHashSet<>();

    public Room(String number, Integer size) {
        this.number = number;
        this.size = size;
    }
}