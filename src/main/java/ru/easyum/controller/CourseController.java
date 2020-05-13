package ru.easyum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.easyum.domain.Course;
import ru.easyum.repository.CourseRepository;
import ru.easyum.service.CourseService;

import java.util.List;


@Controller
public class CourseController {
    @Autowired
    CourseService service;
    @Autowired
    CourseRepository repository;

    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    public String allCourses(@RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "5") Integer pageSize, Model model) {
        Long total = repository.count();
        List<Course> courses = service.getPage(pageNo, pageSize);
        model.addAttribute("courses", courses);
        int size = (int) Math.ceil((double) total / 5);
        model.addAttribute("pages", new Integer[size]);
        return "courses";
    }

    @GetMapping(path = "/course/add")
    public String addCourse(Model model) {
        return "courseAdd";
    }



    @PostMapping(path = "/course/save")
    public String saveCourse(@RequestParam String name, @RequestParam int duration) {
        service.saveCourse(new Course(name, duration));
        return "redirect:/courses";
    }
}
