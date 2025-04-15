package com.wf.userservice.service.impl;

import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.entity.User;
import com.wf.userservice.mapper.UserMapper;
import com.wf.userservice.repository.UserRepository;
import com.wf.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {

    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

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
    public Map<String, Long> getUserCountGroupedByCity() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("city").count().as("count"),
                Aggregation.project("count").and("city").previousOperation()
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "users", Document.class);

        Map<String, Long> cityCounts = new HashMap<>();
        for (Document doc : results.getMappedResults()) {
            cityCounts.put(doc.getString("_id"), doc.getLong("count")); // "_id" will hold group field
        }
        return cityCounts;
    }



    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}