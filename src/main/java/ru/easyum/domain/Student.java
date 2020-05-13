package ru.easyum.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "birth")
    private Date birthDate;
    @Column(name = "score")
    private Long score;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses;

    public Student() {
    }

    public Student(String fio, Date birthDate, Long score, List<Course> courses) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.score = score;
        this.courses = courses;
    }

    public Student(String fio, Date birthDate, Long score) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
