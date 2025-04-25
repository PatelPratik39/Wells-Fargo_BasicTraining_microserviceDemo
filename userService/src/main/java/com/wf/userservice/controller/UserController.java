package com.wf.userservice.controller;

import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brave.Tracer;

import java.util.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User management APIs")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final Tracer tracer;

    @Operation(summary = "Create a User")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        logger.info("Attempting to create a new user: {}", userDTO);
        String traceId = tracer.currentSpan().context().traceIdString();
        log.info("📌 Creating user, traceId={}", traceId);
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            logger.debug("New user created successfully with ID: {}", createdUser.getId());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to create user. Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get User by Id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        logger.info("Fetching details for user ID: {}", id);
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            logger.warn("No user found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.debug("User data retrieved: {}", user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser(){
        logger.info("Initiating request to retrieve all users");
        List<UserDTO> users = userService.getAllUsers();
        if (users.isEmpty()) {
            logger.info("No users found in the system");
        } else {
            logger.debug("Fetched {} user(s)", users.size());
        }
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Update users")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO){
        logger.info("Received request to update user with ID: {}", id);
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            logger.debug("User with ID {} updated successfully: {}", id, updatedUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Error occurred while updating user with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Count city group by city name")
    @GetMapping("/states/city")
    public ResponseEntity<Map<String, Long>> getUserStatesByCity(){
        logger.info("Request received to fetch user distribution by city");
        Map<String, Long> cityStats = userService.getUserCountGroupedByCity();
        logger.debug("City distribution: {}", cityStats);
        return ResponseEntity.ok(cityStats);
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        logger.info("Processing request to delete user with ID: {}", id);
        try {
            userService.deleteUser(id);
            logger.info("User with ID {} has been successfully removed", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Failed to delete user with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}