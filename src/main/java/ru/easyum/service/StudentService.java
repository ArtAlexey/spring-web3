package ru.easyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easyum.domain.Student;
import ru.easyum.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student findStudent(int id) {
        return repository.findById(id).get();
    }

    public List<Student> findAllStudents() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> getPage(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Student> pageStudents = repository.findAll(pageable);
        if(pageStudents.hasContent()){
            return pageStudents.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }
}
