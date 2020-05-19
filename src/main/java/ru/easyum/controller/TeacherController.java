package ru.easyum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easyum.MyUserPrincipal;
import ru.easyum.domain.Teacher;
import ru.easyum.repository.TeacherRepository;
import ru.easyum.service.TeacherService;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherRepository repository;
    @Autowired
    TeacherService service;

    @RequestMapping(path = "/teachers", method = RequestMethod.GET)
    public String allTeachers(@RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "5") Integer pageSize, Model model) {
        Long total = repository.count();
        List<Teacher> teachers = service.getPage(pageNo, pageSize);
        model.addAttribute("teachers", teachers);
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user.getUsername());
        int size = (int) Math.ceil((double) total / 5);
        model.addAttribute("pages", new Integer[size]);
        return "teachers";

    }
    @GetMapping(path = "/teacher/add")
    public String addCourse(Model model) {
        return "teacherAdd";
    }

    @PostMapping(path = "/teacher/save")
    public String saveTeacher(@RequestParam String fio, @RequestParam String about) {
        service.saveTeacher(new Teacher(fio, about));
        return "redirect:/teachers";
    }
}
