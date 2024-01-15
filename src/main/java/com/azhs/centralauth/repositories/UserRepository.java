package com.azhs.centralauth.repositories;

import com.azhs.centralauth.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Custom queries can be added here if needed
}