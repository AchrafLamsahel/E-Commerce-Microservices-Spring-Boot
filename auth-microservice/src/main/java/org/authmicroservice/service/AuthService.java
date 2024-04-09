package org.authmicroservice.service;

import lombok.AllArgsConstructor;
import org.authmicroservice.client.UserServiceClient;
import org.authmicroservice.dto.*;
import org.authmicroservice.enums.CustomerMessageError;
import org.authmicroservice.enums.CustomerMessageValidator;
import org.authmicroservice.exceptions.DataNotValidException;
import org.authmicroservice.exceptions.EmailOrPasswordIncorrectException;
import org.authmicroservice.utils.InputValidatorRegister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO request) {
        UserDTO userDTO = userServiceClient.getUserByEmail(request.getEmail()).getBody();
        if (userDTO != null && passwordEncoder.matches(request.getPassword(), userDTO.getPassword())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                return LoginResponseDTO.builder()
                        .accessToken(jwtService.generateToken(request.getEmail()))
                        .refreshToken(jwtService.generateRefreshToken(request.getEmail()))
                        .build();
            } else {
                throw new EmailOrPasswordIncorrectException("Wrong credentials");
            }
        } else {
            throw new EmailOrPasswordIncorrectException("Wrong credentials");
        }
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        if (InputValidatorRegister.isValidPassword(request.getPassword()))
            throw new DataNotValidException(CustomerMessageError.PASSWORD_LENGTH_ERROR.getMessage());
        if (!InputValidatorRegister.isValidMoroccanPhoneNumber(request.getNumberPhone()))
            throw new DataNotValidException(CustomerMessageError.PHONE_NUMBER_NOT_VALID.getMessage());
        if (!InputValidatorRegister.isValidEmail(request.getEmail()))
            throw new DataNotValidException(CustomerMessageError.EMAIL_IS_INVALID.getMessage());
        if (InputValidatorRegister.isNull(request.getFirstname()))
            throw new DataNotValidException(CustomerMessageError.FIRSTNAME_IS_REQUIRED.getMessage());
        if (InputValidatorRegister.isNull(request.getLastname()))
            throw new DataNotValidException(CustomerMessageError.LASTNAME_IS_REQUIRED.getMessage());
        if (userServiceClient.existsByEmail(request.getEmail()))
            throw new DataNotValidException(CustomerMessageError.EMAIL_ALREADY_EXIST.getMessage());
        userServiceClient.save(request).getBody();
        // email for validation
        return RegisterResponseDTO.builder()
                .message(CustomerMessageValidator.SAVED_SUCCESSFULLY.getMessage()+" "
                        + CustomerMessageValidator.CHECK_EMAIL_FOR_VALIDATION.getMessage())
                .build();
    }

}
