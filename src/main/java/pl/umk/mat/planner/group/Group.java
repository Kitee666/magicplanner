package pl.umk.mat.planner.group;

import pl.umk.mat.planner.subject.Subject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "rok", nullable = false)
    private Integer rok;

    @Column(name = "size", nullable = false)
    private Integer size;

    @OneToMany(mappedBy = "group")
    private List<Subject> subjects = new java.util.ArrayList<>();

    public Group() {
    }

    public Group(String type, Integer rok, Integer size, List<Subject> subjects) {
        this.type = type;
        this.rok = rok;
        this.size = size;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }


}