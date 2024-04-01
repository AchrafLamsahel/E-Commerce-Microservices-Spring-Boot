package org.usermicroservice.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import org.usermicroservice.services.IUserService;
import java.util.Collection;

@RestController
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @GetMapping("/")
    public Collection<UserDTO> getAllUsers() {
        return iUserService.getAllUsers();
    }

    @GetMapping("/Active")
    public Collection<UserDTO> getAllUsersActive() {
        return iUserService.getAllUsersActive();
    }

    @GetMapping("/InActive")
    public Collection<UserDTO> getAllUsersInActive() {
        return iUserService.getAllUserInActive();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody User user) {
        iUserService.registerUser(user);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(iUserService.getUserByEmail(email));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        iUserService.deleteUserById(id);
    }

}
