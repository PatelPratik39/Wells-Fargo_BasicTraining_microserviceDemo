package com.wf.userservice.repository;

import com.wf.userservice.dto.UserDTO;
import com.wf.userservice.entity.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

//    List<User> findByCity(String city);
//
//    List<User> findBySalesGreaterThan(int sales);
//
//    List<UserDTO> countUserByCity();

}