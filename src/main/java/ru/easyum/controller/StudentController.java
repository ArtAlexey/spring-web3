package ru.easyum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easyum.MyUserPrincipal;
import ru.easyum.domain.Course;
import ru.easyum.domain.Student;
import ru.easyum.repository.StudentRepository;
import ru.easyum.service.StudentService;

import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentService service;
    @Autowired
    StudentRepository repository;

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String allStudents(@RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "5") Integer pageSize, Model model) {
        Long total = repository.count();
        List<Student> students = service.getPage(pageNo, pageSize);
        model.addAttribute("students", students);
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user.getUsername());
        int size = (int) Math.ceil((double) total / 5);
        model.addAttribute("pages", new Integer[size]);
        return "students";
    }

    @GetMapping(path = "/student/add")
    public String addStudent(Model model) {
        return "studentAdd";
    }

    @PostMapping(path = "/student/save")
    public String saveStudent(@RequestParam String fio, @RequestParam Date birthDate, @RequestParam Long score) {
        service.saveStudent(new Student(fio, birthDate, score));
        return "redirect:/students";
    }
}
