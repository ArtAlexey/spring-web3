package ru.easyum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easyum.domain.Course;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {
    List<Course> findByName(String name);
}
