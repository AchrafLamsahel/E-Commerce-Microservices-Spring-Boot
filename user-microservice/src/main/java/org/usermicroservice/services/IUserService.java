package org.usermicroservice.services;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUsersActive();
    List<UserDTO> getAllUserInActive();
    void registerUser(User user) throws MessagingException;
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    void deleteUserById(Long id);
    UserDTO updateUser(Long id,User user);
    boolean existsByEmail(String email);
    ResponseEntity<?> confirmEmail(String confirmationToken);
}
