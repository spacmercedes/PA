package com.example.productmanagement.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
}
