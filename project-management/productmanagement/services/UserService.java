package com.example.productmanagement.services;

import com.example.productmanagement.dtos.UserDTO;

public interface UserService {

    /**
     * Service method for creating new user
     * @param userDTO {@link UserDTO}
     */
    void createUser(UserDTO userDTO);

}
