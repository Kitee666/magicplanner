package pl.umk.mat.planner.lecturer;

import pl.umk.mat.planner.subject.Subject;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @Column(name = "subject", columnDefinition = "TEXT")
    private String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Lecturer() {
    }

    public Lecturer(String name, String lastname, String subject) {
        this.name = name;
        this.lastname = lastname;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}