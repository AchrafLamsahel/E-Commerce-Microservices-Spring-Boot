package org.usermicroservice.web;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import org.usermicroservice.services.IUserService;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {return ResponseEntity.ok(iUserService.getAllUsers());}

    @GetMapping("/Active")
    public ResponseEntity<List<UserDTO>> getAllUsersActive() {return ResponseEntity.ok(iUserService.getAllUsersActive());}

    @GetMapping("/InActive")
    public ResponseEntity<List<UserDTO>> getAllUsersInActive() {return ResponseEntity.ok(iUserService.getAllUserInActive());}

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {return ResponseEntity.ok(iUserService.getUserById(id));}

    @PostMapping("/register")
    public void createUser(@RequestBody User user) throws MessagingException {iUserService.registerUser(user);}

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {return ResponseEntity.ok(iUserService.getUserByEmail(email));}

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        iUserService.deleteUserById(id);
    }

    @GetMapping("/existsByEmail/{email}")
    public boolean existsByEmail(@PathVariable String email) {return iUserService.existsByEmail(email);}

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {return ResponseEntity.ok(iUserService.updateUser(id, user));}

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {return iUserService.confirmEmail(confirmationToken);}

}
