package org.authmicroservice.service;

import lombok.AllArgsConstructor;
import org.authmicroservice.client.UserServiceClient;
import org.authmicroservice.dto.LoginRequestDTO;
import org.authmicroservice.dto.LoginResponseDTO;
import org.authmicroservice.dto.RegisterRequestDTO;
import org.authmicroservice.dto.RegisterResponseDTO;
import org.authmicroservice.exceptions.EmailOrPasswordIncorrectException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService{
    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authenticate.isAuthenticated())
            return LoginResponseDTO
                    .builder()
                    .accessToken(jwtService.generateToken(request.getEmail()))
                    .build();
        else throw new EmailOrPasswordIncorrectException("Wrong credentials");
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        return userServiceClient.save(request).getBody();
    }
}
