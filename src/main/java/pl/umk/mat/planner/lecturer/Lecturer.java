package pl.umk.mat.planner.lecturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umk.mat.planner.connector.Connector;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lecturer")
public class Lecturer {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private Set<Connector> connectors = new LinkedHashSet<>();

    public Lecturer(String name, String lastname, String title) {
        this.name = name;
        this.lastname = lastname;
        this.title = title;
    }

    public String getFullName() {
        return this.title + " " + this.name + " " + this.lastname;
    }
}