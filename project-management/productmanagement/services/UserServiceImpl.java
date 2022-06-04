package com.example.productmanagement.services;

import com.example.productmanagement.dtos.UserDTO;
import com.example.productmanagement.entities.UserEntity;
import com.example.productmanagement.exceptions.UserAlreadyExistsException;
import com.example.productmanagement.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;

    @Autowired
    UserServiceImpl(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    /**
     * Save a user in database, if and only if it's email address has not been already used
     * @param userDTO {@link UserDTO}
     * @throws UserAlreadyExistsException if email address has been already taken
     */
    @Override
    public void createUser(UserDTO userDTO) {
        var entity = userRepo.findUserByEmail(userDTO.getEmail());
        if(entity.isPresent()){
            throw new UserAlreadyExistsException("Email already taken. Please try again.");
        }
        userRepo.save(mapper.map(userDTO, UserEntity.class));
    }
}
