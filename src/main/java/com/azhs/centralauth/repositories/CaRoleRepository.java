package com.azhs.centralauth.repositories;

import com.azhs.centralauth.entities.CaRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaRoleRepository extends MongoRepository<CaRole, String> {
    // Custom queries can be added here if needed
}