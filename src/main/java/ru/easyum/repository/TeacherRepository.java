package ru.easyum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easyum.domain.Teacher;

import java.util.List;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

    List<Teacher> findByFio(String fio);

    @Query("select t from Teacher t where t.about like %?1%")
    List<Teacher> findByAboutPartialMatch(String about);
}
