package ru.easyum.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEACHER")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "about")
    private String about;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String fio, String about, List<Course> courses) {
        this.fio = fio;
        this.about = about;
        this.courses = courses;
    }

    public Teacher(String fio, String about) {
        this.fio = fio;
        this.about = about;
    }

    public List<Course> getCourses() {
        return courses;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
