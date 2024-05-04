package org.authmicroservice.web;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.authmicroservice.dto.*;
import org.authmicroservice.service.AuthService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {return ResponseEntity.ok(authService.login(request));}

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {return ResponseEntity.ok(authService.register(request));}

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> validationEmail(@RequestParam("token") String confirmationToken) {
        return authService.confirmEmail(confirmationToken);
    }

    @PostMapping("/recuperer-mot-de-passe")
    public ResponseEntity<?> handleResetPassword(@RequestParam("email") String email) throws MessagingException {
        return authService.handleResetPassword(email);
    }

    @PostMapping("/changer-mot-de-passe")
    public ResponseEntity<?> handleChangePassword(@RequestParam("token") String token, @RequestBody ChangePasswordDTO changePasswordDTO) {
        changePasswordDTO.setToken(token);
        authService.handleChangePassword(changePasswordDTO);
        return ResponseEntity.ok("Le mot de passe a été changé avec succès");
    }

}
