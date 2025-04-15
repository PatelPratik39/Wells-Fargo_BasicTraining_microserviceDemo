package com.wf.userservice.service;

import com.wf.userservice.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(String id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);

}