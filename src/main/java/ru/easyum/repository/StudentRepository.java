package ru.easyum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easyum.domain.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
