package org.usermicroservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.CustomerMessageError;
import org.usermicroservice.enums.Role;
import org.usermicroservice.exceptions.DataNotValidException;
import org.usermicroservice.exceptions.UserNotFoundException;
import org.usermicroservice.mappers.UserMapper;
import org.usermicroservice.repositories.UserRepository;
import org.usermicroservice.utils.InputValidatorRegister;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.usersToUsersDto(userRepository.findAll());
    }

    @Override
    public UserDTO registerUser(User user) {
        log.info("Creating new user with email : {}", user.getEmail());
        if (InputValidatorRegister.isValidPassword(user.getPassword()))
            throw new DataNotValidException(CustomerMessageError.PASSWORD_LENGTH_ERROR.getMessage());
        if (InputValidatorRegister.isValidMoroccanPhoneNumber(user.getNumberPhone()))
            throw new DataNotValidException(CustomerMessageError.PHONE_NUMBER_NOT_VALID.getMessage());
        if (InputValidatorRegister.isValidEmail(user.getEmail()))
            throw new DataNotValidException(CustomerMessageError.EMAIL_IS_INVALID.getMessage());
        if (InputValidatorRegister.isNull(user.getFirstname()))
            throw new DataNotValidException(CustomerMessageError.FIRSTNAME_IS_REQUIRED.getMessage());
        if (InputValidatorRegister.isNull(user.getLastname()))
            throw new DataNotValidException(CustomerMessageError.LASTNAME_IS_REQUIRED.getMessage());
        User toSave = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .numberPhone(user.getNumberPhone())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.USER)
                .isActive(Active.ACTIVE)
                .build();
        return UserMapper.userToDto(userRepository.save(toSave));
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

    }

    @Override
    public UserDTO updateUser(User user) {
        return null;
    }


}
