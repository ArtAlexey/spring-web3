package ru.easyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.easyum.domain.Authority;
import ru.easyum.domain.User;
import ru.easyum.repository.AuthorityRepository;
import ru.easyum.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public User findUser(Long id) {
        return repository.findById(id).get();
    }

    public List<User> getPage(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pageUsers = repository.findAll(paging);
        if(pageUsers.hasContent()) {
            return pageUsers.getContent();
        } else {
            return new ArrayList<User>();
        }
    }

    public List<User> findAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveUser(User user, Authority authority) {
        repository.save(user);
        authorityRepository.save(authority);
    }

    @Transactional
    public void deleteUser(User user, Authority authority) {
        authorityRepository.delete(authority);
        repository.delete(user);
    }
}
