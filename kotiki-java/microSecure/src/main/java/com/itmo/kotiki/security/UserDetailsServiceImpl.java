package com.itmo.kotiki.security;

import com.itmo.kotiki.dataAccessObject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        usersRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new
                UserDetailsImpl(usersRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new));
    }
}
