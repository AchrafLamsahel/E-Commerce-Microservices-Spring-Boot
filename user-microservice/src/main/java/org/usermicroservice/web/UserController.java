package org.usermicroservice.web;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.apache.http.auth.AUTH;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usermicroservice.dtos.ChangePasswordDTO;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.ERole;
import org.usermicroservice.services.IUserService;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final IUserService iUserService;

    /**
     * path : (GET) --> http://localhost:8081/users/
     * @return
     */

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(iUserService.getAllUsers());
    }
    /**
     * path : (GET) --> http://localhost:8081/users/{id}
     * @return
     */


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(iUserService.getUserById(id));
    }

    @PostMapping("/register")
    public void createUser(@RequestBody User user) throws MessagingException {
        iUserService.registerUser(user);
    }

    /**
     * path : (GET) --> http://localhost:8081/users/getUserByEmail/{email}
     * @return
     */

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(iUserService.getUserByEmail(email));
    }

    /**
     * path : (GET) --> http://localhost:8081/users/existsByEmail/{email}
     * @return
     */
    @GetMapping("/existsByEmail/{email}")
    public boolean existsByEmail(@PathVariable String email) {
        return iUserService.existsByEmail(email);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return iUserService.confirmEmail(confirmationToken);
    }

    @PostMapping("/recuperer-mot-de-passe")
    public ResponseEntity<String> handleResetPassword(@RequestParam("email") String email) throws MessagingException {
        iUserService.resetPassword(email);
        return ResponseEntity.ok("Un email de réinitialisation a été envoyé à " + email);
    }

    @PostMapping("/changer-mot-de-passe")
    public ResponseEntity<String> handleChangePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        iUserService.changePassword(changePasswordDTO);
        return ResponseEntity.ok("Le mot de passe a été changé avec succès");
    }

    /** -------------------------- ADMIN -----------------------------  */
    @GetMapping("/admin/Active")
    public ResponseEntity<List<UserDTO>> getAllUsersActive() {
        return ResponseEntity.ok(iUserService.getAllUsersActive());
    }

    @GetMapping("/admin/InActive")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsersInActive() {
        return ResponseEntity.ok(iUserService.getAllUserInActive());
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(iUserService.updateUser(id, user));
    }

    @PutMapping("/admin/add-role")
    public ResponseEntity<?> addRoleToUserByEmail(@RequestParam ERole eRole, @RequestParam String email) {
        iUserService.addRoleToUserByEmail(eRole, email);
        return ResponseEntity.ok("Role added successfully.");
    }

    @PutMapping("/admin/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        iUserService.activeUser(id);
        return ResponseEntity.ok("User activated successfully.");
    }

    @PutMapping("/admin/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        iUserService.inActiveUser(id);
        return ResponseEntity.ok("User deactivated successfully.");
    }

    /**
     * path : (Delete) --> http://localhost:8081/users/admin/{id}
     * @return
     */
    @DeleteMapping("/admin/{id}")
    public void deleteUserById(@PathVariable Long id) {
        iUserService.deleteUserById(id);
    }

}
