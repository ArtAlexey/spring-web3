package ru.easyum.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easyum.domain.Student;
import ru.easyum.domain.Teacher;
import ru.easyum.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public Teacher findTeacher(int id) {
        return repository.findById(id).get();
    }

    public List<Teacher> findAllTeachers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void saveTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public List<Teacher> getPage(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Teacher> pageTeachers = repository.findAll(pageable);
        if(pageTeachers.hasContent()) {
            return pageTeachers.getContent();
        } else {
            return new ArrayList<Teacher>();
        }
    }
}