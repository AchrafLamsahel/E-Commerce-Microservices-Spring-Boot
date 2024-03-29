package org.usermicroservice.mappers;

import org.usermicroservice.dtos.UserDTO;
import org.usermicroservice.entities.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO userToDto(User user){
        if(user == null ) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setNumberPhone(user.getNumberPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setIsActive(user.getIsActive());
        return userDTO;
    }

    public static User dtoToUser(UserDTO userDTO){
        if(userDTO == null ) return null;
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setNumberPhone(userDTO.getNumberPhone());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setIsActive(userDTO.getIsActive());
        return user;
    }

    public static List<User> usersDtoToUsers(List<UserDTO> userDTOList) {
        if (userDTOList == null || userDTOList.isEmpty()) return null;
        return userDTOList.stream().map(UserMapper::dtoToUser)
                .collect(Collectors.toList());
    }

    public static List<UserDTO> usersToUsersDto(List<User> users){
        if(users == null || users.isEmpty())return null;
        return users.stream().map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }

}
