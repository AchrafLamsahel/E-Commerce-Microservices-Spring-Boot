package org.usermicroservice.services;

import org.springframework.stereotype.Service;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Role;

import java.util.List;

@Service
public interface IUserService {
    List<UserDTO> getAllUsers();

    UserDTO registerUser(User user);

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    void deleteUserById(Long id);

    UserDTO updateUser(User user);

}
