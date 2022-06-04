package com.example.productmanagement.services;

import com.example.productmanagement.entities.UserEntity;
import com.example.productmanagement.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userEntityOptional = userRepo.findUserByEmail(email);

        if (userEntityOptional.isEmpty()) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database.");
        }
        UserEntity userEntity = userEntityOptional.get();
        log.info("User found in the database");
        return new User(userEntity.getEmail(), userEntity.getPassword(), emptyList());
    }
}
