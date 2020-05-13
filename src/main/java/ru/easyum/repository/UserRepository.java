package ru.easyum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.easyum.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);


}
