package com.wf.userservice.mapper;

import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.entity.User;

public class UserMapper {

//    User Entity class to UserDto class
    public static UserDTO mapToUserDTO(User user) {

        if(user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setSales(user.getSales());
        return userDTO;
    }


//    UserDTO class to UserEntity class
    public static User mapToUser(UserDTO userDTO) {
        if(userDTO == null) return null;

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setSales(userDTO.getSales());
        return user;
    }
}