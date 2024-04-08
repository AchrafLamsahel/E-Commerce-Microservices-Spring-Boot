package org.usermicroservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.emails.IMailService;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.CustomerMessageError;
import org.usermicroservice.enums.CustumerEmailMessage;
import org.usermicroservice.enums.Role;
import org.usermicroservice.exceptions.DataNotValidException;
import org.usermicroservice.exceptions.UserNotFoundException;
import org.usermicroservice.mappers.UserMapper;
import org.usermicroservice.repositories.UserRepository;
import org.usermicroservice.utils.InputValidatorRegister;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final InputValidatorRegister inputValidatorRegister;
    private final IMailService iMailService;

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
        /**
        if (InputValidatorRegister.isValidPassword(user.getPassword()))
            throw new DataNotValidException(CustomerMessageError.PASSWORD_LENGTH_ERROR.getMessage());
        if (!InputValidatorRegister.isValidMoroccanPhoneNumber(user.getNumberPhone()))
            throw new DataNotValidException(CustomerMessageError.PHONE_NUMBER_NOT_VALID.getMessage());
        if (!InputValidatorRegister.isValidEmail(user.getEmail()))
            throw new DataNotValidException(CustomerMessageError.EMAIL_IS_INVALID.getMessage());
        if (InputValidatorRegister.isNull(user.getFirstname()))
            throw new DataNotValidException(CustomerMessageError.FIRSTNAME_IS_REQUIRED.getMessage());
        if (InputValidatorRegister.isNull(user.getLastname()))
            throw new DataNotValidException(CustomerMessageError.LASTNAME_IS_REQUIRED.getMessage());
        if (inputValidatorRegister.isEmailAlreadyExist(user.getEmail()))
            throw new DataNotValidException(CustomerMessageError.EMAIL_ALREADY_EXIST.getMessage());
         */
        User toSave = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .numberPhone(user.getNumberPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.USER)
                .isActive(Active.ACTIVE)
                .build();
        UserMapper.userToDto(userRepository.save(toSave));
        log.info("User with email {} saved successfully", toSave.getEmail());
        //iMailService.sendMail(user.getEmail(),
        //        CustumerEmailMessage.PROFILE_SAVED_SUCCESSFULLY.getMessage(),
         //       "Test Message ----> ******");
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


}
