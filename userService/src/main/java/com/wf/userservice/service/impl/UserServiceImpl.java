package com.wf.userservice.service.impl;

import com.wf.userservice.dto.NotificationDTO;
import com.wf.userservice.dto.OrderDTO;
import com.wf.userservice.dto.ProductDTO;
import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.entity.User;
import com.wf.userservice.mapper.UserMapper;
import com.wf.userservice.repository.UserRepository;
import com.wf.userservice.service.UserService;
import com.wf.userservice.service.client.NotificationServiceClient;
import com.wf.userservice.service.client.OrderServiceClient;
import com.wf.userservice.service.client.ProductServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.stereotype.Service;
import org.bson.Document;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {

    private UserRepository userRepository;
    private MongoTemplate mongoTemplate;
    private final ProductServiceClient productServiceClient;
    private final OrderServiceClient orderServiceClient;
    private final NotificationServiceClient notificationServiceClient;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Use static mapper directly
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }

        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);

        // --- Inter-service communication ---
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("iPhone 15 Pro");
        productDTO.setDescription("Latest Apple phone");
        productDTO.setPrice(1199.99);
        productDTO.setQuantity(10);
        productDTO.setAvailable(true);
        OrderDTO orderDTO = OrderDTO.builder()
                .userId(savedUser.getId())
                .product("iPhone 15 Pro") // or get from productDTO
                .quantity(1)
                .price(1199.99) // or get from productDTO
                .status("PENDING")
                .build();

        NotificationDTO notificationDTO = new NotificationDTO(
                savedUser.getEmail(),
                "Welcome",
                "Your profile has been created and order initiated.",
                new HashMap<>()
        );

        log.info("Creating product: {}", productDTO);
        productServiceClient.createProduct(productDTO);

        log.info("Creating order: {}", orderDTO);
        orderServiceClient.createOrder(orderDTO);

        log.info("Sending notification: {}", notificationDTO);
        notificationServiceClient.sendNotification(notificationDTO);



        return UserMapper.mapToUserDTO(savedUser);
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

            return cityCounts;
        } catch (Exception e) {
            e.printStackTrace(); // Or use log.error("Aggregation error", e);
            throw e;
        }
    }



    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}