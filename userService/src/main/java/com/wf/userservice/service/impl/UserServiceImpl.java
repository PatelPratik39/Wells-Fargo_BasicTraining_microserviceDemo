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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Creating user: {}", userDTO);

        if (userDTO == null) {
            logger.warn("UserDTO is null during createUser call");
            throw new IllegalArgumentException("UserDTO is null");
        }

        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);

        logger.debug("User successfully saved with ID: {}", savedUser.getId());
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(String id) {
        logger.info("Retrieving user with ID: {}", id);

        User user = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with ID: {}", id);
            return new RuntimeException("User not found");
        });

        logger.debug("User retrieved: {}", user);
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        logger.info("Fetching all users from database");

        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());

        logger.debug("Total users found: {}", users.size());
        return users;
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) {
        logger.info("Updating user with ID: {}", id);

        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found for update with ID: {}", id);
            return new RuntimeException("User not found");
        });

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setCity(userDTO.getCity());
        existingUser.setSales(userDTO.getSales());

        User updatedUser = userRepository.save(existingUser);

        logger.debug("User with ID {} updated successfully", id);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public Map<String, Long> getUserCountGroupedByCity() {
        logger.info("Aggregating user count grouped by city");

        try {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.group("city").count().as("count")
            );

            AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "users", Document.class);

            Map<String, Long> cityCounts = new HashMap<>();
            for (Document doc : results.getMappedResults()) {
                Object idObj = doc.get("_id");
                String city = idObj != null ? idObj.toString() : "Unknown";
                Number count = doc.get("count", Number.class);
                cityCounts.put(city, count != null ? count.longValue() : 0L);
            }

            logger.debug("City-wise user count: {}", cityCounts);
            return cityCounts;
        } catch (Exception e) {
            logger.error("Error occurred during aggregation by city: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteUser(String id) {
        logger.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        logger.debug("User with ID {} deleted successfully", id);
    }
}
