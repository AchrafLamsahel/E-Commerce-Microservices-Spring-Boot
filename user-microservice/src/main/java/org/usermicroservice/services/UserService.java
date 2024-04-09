package org.usermicroservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.emails.IMailService;
import org.usermicroservice.emails.MailService;
import org.usermicroservice.entities.ConfirmationToken;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.CustomerMessageError;
import org.usermicroservice.enums.Role;
import org.usermicroservice.exceptions.UserNotFoundException;
import org.usermicroservice.mappers.UserMapper;
import org.usermicroservice.repositories.ConfirmationTokenRepository;
import org.usermicroservice.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final IMailService iMailService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.mail.email}")
    private static  String email;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.usersToUsersDto(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getAllUsersActive() {
        return UserMapper.usersToUsersDto(userRepository.findAll()
                .stream()
                .filter(user ->
                        user.getIsActive().equals(Active.ACTIVE))
                .collect(Collectors.toList()));
    }

    @Override
    public List<UserDTO> getAllUserInActive() {
        return UserMapper.usersToUsersDto(userRepository.findAll()
                .stream()
                .filter(user ->
                        user.getIsActive().equals(Active.INACTIVE))
                .collect(Collectors.toList()));
    }

    @Override
    public void registerUser(User user) {
        log.info("Creating new user with email : {}", user.getEmail());
        User toSave = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .numberPhone(user.getNumberPhone())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .isActive(Active.ACTIVE)
                .isEnabled(false)
                .build();
        //UserMapper.userToDto(userRepository.save(toSave));
        ConfirmationToken confirmationToken = new ConfirmationToken(toSave);
        confirmationTokenRepository.save(confirmationToken);
        /**
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(email);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8081/confirm-account?token=" + confirmationToken.getConfirmationToken());
        mailService.sendEmail(mailMessage);
         */
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        CustomerMessageError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        return UserMapper.userToDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new
                        UserNotFoundException(
                        CustomerMessageError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + email));
        return UserMapper.userToDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        CustomerMessageError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        //user.setEmail("###" + user.getEmail());
        user.setIsActive(Active.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public UserDTO updateUser(Long id, User user) {
        log.info("Updating user with id: {}", id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        CustomerMessageError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setNumberPhone(user.getNumberPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(existingUser);
        log.info("User with id {} updated successfully", id);
        return UserMapper.userToDto(updatedUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }


}
