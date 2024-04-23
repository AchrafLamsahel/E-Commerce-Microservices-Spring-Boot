package org.usermicroservice.services;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.usermicroservice.dtos.ChangePasswordDTO;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.emails.IMailService;
import org.usermicroservice.entities.Role;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.CustomerEmailMessage;
import org.usermicroservice.enums.CustomerMessageError;
import org.usermicroservice.enums.ERole;
import org.usermicroservice.exceptions.DataNotValidException;
import org.usermicroservice.exceptions.RoleNotFoundException;
import org.usermicroservice.exceptions.UserNotFoundException;
import org.usermicroservice.mappers.UserMapper;
import org.usermicroservice.repositories.RoleRepository;
import org.usermicroservice.repositories.UserRepository;
import org.usermicroservice.utils.TokenGenerator;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IMailService iMailService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Get all users");
        return UserMapper.usersToUsersDto(userRepository.findAll());
    }

    @Override
    public List<UserDTO> getAllUsersActive() {
        log.info("Get All Users Active  ");
        return UserMapper.usersToUsersDto(userRepository.findAll()
                .stream()
                .filter(user -> user.getIsActive().equals(Active.ACTIVE))
                .collect(Collectors.toList()));
    }

    @Override
    public List<UserDTO> getAllUserInActive() {
        log.info("Get All Users InActive ");
        return UserMapper.usersToUsersDto(userRepository.findAll()
                .stream()
                .filter(user -> user.getIsActive().equals(Active.INACTIVE))
                .collect(Collectors.toList()));
    }

    @Override
    public void registerUser(User user) throws MessagingException {
        log.info("Creating new user with email : {}", user.getEmail());
        Role role= roleRepository.findByRole(ERole.valueOf(ERole.ADMIN.name())).orElseThrow(
                () -> new RoleNotFoundException(CustomerMessageError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + user.getEmail()));
        User toSave = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .numberPhone(user.getNumberPhone())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(List.of(role))
                .isActive(Active.ACTIVE)
                .isEnabled(false)
                .confirmationToken(TokenGenerator.generateToken())
                .build();
        userRepository.save(toSave);
        iMailService.sendConfirmationEmail(toSave, "ecommercemicroservice2024@gmail.com");
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
        log.info("Fetching user by email : {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new
                        UserNotFoundException(
                        CustomerMessageError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + email));
        return UserMapper.userToDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deactivate user by id: {}", id);
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
        //existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(existingUser);
        log.info("User with id {} updated successfully", id);
        return UserMapper.userToDto(updatedUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Check existing user by email: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity<String> confirmEmail(String confirmationToken) {
        log.info("Confirmation Email ");
        User user = userRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid token!"));
        user.setVerifiedAt(new Date());
        user.setEnabled(true);
        user.setConfirmationToken(null);
        userRepository.save(user);
        return ResponseEntity.ok("Your Email confirmed successfully.");
    }

    @Override
    public void resetPassword(String email) throws MessagingException {
        log.info("Reset Password  ");
        if (email == null) throw new
                DataNotValidException(CustomerMessageError.EMAIL_IS_REQUIRED.getMessage());
        User user = userRepository.findByEmail(email.toLowerCase()).orElse(null);
        if (user != null  && user.getVerifiedAt() != null && user.isEnabled()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, 24);
            Date expiryDate = calendar.getTime();
            user.setResetPasswordTokenExpiryDate(expiryDate);
            user.setResetPasswordToken(TokenGenerator.generateToken());
            updateUser(user.getUserId(), user);
            UserDTO userDTO = UserMapper.userToDto(user);
            iMailService.sendResetPasswordMail(user.getEmail(),
                    CustomerEmailMessage.RESET_PASSWORD_SUBJECT.getMessage(),userDTO);
        } else {
            throw new RuntimeException("No account found with this email address!");
        }
    }

    @Override
    public void changePassword(ChangePasswordDTO dto) {
        log.info("Change password : {}","********");
        User appUser = userRepository.findByResetPasswordToken(dto.getToken()).orElseThrow();
        if ( appUser != null ) {
            if (appUser.getResetPasswordTokenExpiryDate() != null &&
                    appUser.getResetPasswordTokenExpiryDate().before(new Date())) {
                throw new RuntimeException("Le jeton de réinitialisation de mot de passe a expiré.");
            }
            appUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            appUser.setResetPasswordToken(null);
            appUser.setResetPasswordTokenExpiryDate(null);
            updateUser(appUser.getUserId(), appUser);
            UserMapper.userToDto(appUser);
        }
    }

    @Override
    public void addRoleToUserByEmail(ERole eRole, String email) {
        log.info("Add Role to  user with email : {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                CustomerMessageError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + email
        ));
        Role role = roleRepository.findByRole(eRole).orElseThrow(() -> new RoleNotFoundException(
                CustomerMessageError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + eRole
        ));
        user.getRoles().add(role);
    }

    @Override
    public UserDTO activeUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(CustomerMessageError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        if (user.getIsActive() == Active.ACTIVE) {
            return UserMapper.userToDto(user);
        }else{
            user.setIsActive(Active.INACTIVE);
            return UserMapper.userToDto(user);
        }
    }

    @Override
    public UserDTO inActiveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(CustomerMessageError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        if (user.getIsActive() == Active.INACTIVE) {
            return UserMapper.userToDto(user);
        }else{
            user.setIsActive(Active.INACTIVE);
            return UserMapper.userToDto(user);
        }
    }

}


