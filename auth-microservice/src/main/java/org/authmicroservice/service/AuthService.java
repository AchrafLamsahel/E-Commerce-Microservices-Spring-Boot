package org.authmicroservice.service;

import lombok.AllArgsConstructor;
import org.authmicroservice.client.UserServiceClient;
import org.authmicroservice.dto.LoginRequestDTO;
import org.authmicroservice.dto.LoginResponseDTO;
import org.authmicroservice.dto.RegisterRequestDTO;
import org.authmicroservice.dto.RegisterResponseDTO;
import org.authmicroservice.enums.CustomerMessageError;
import org.authmicroservice.exceptions.DataNotValidException;
import org.authmicroservice.exceptions.EmailOrPasswordIncorrectException;
import org.authmicroservice.utils.InputValidatorRegister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
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
                    .refreshToken(jwtService.generateRefreshToken(request.getEmail()))
                    .build();
        else throw new EmailOrPasswordIncorrectException("Wrong credentials");
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
        return userServiceClient.save(request).getBody();
    }

}
