package com.wf.userservice.service.impl;

import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.entity.User;
import com.wf.userservice.mapper.UserMapper;
import com.wf.userservice.repository.UserRepository;
import com.wf.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {

    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        return UserMapper.mapToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("User not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        User existingUser  = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setCity(userDTO.getCity());
        existingUser.setSales(userDTO.getSales());
        return UserMapper.mapToUserDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}