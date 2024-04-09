package org.authmicroservice.service;

import org.authmicroservice.dto.LoginRequestDTO;
import org.authmicroservice.dto.LoginResponseDTO;
import org.authmicroservice.dto.RegisterRequestDTO;
import org.authmicroservice.dto.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    LoginResponseDTO login(LoginRequestDTO request);

    RegisterResponseDTO register(RegisterRequestDTO request);

    ResponseEntity<?> confirmEmail(String confirmationToken);

}
