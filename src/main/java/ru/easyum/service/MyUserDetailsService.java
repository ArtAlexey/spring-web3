package ru.easyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.easyum.MyUserPrincipal;
import ru.easyum.domain.Authority;
import ru.easyum.domain.User;
import ru.easyum.repository.AuthorityRepository;
import ru.easyum.repository.UserRepository;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<Authority> authorities = authorityRepository.findAllByUsername(user.getUsername());
        return new MyUserPrincipal(user, authorities);
    }
}
