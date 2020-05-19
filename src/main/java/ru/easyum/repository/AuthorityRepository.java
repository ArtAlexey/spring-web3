package ru.easyum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easyum.domain.Authority;

import java.util.List;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Integer> {

    List<Authority> findAllByUsername(String username);
    Authority findByUsername(String username);


}
