package com.azhs.centralauth.repositories;

import com.azhs.centralauth.entities.CaPermission;
import com.azhs.centralauth.entities.CaRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CaRoleRepository extends MongoRepository<CaRole, String> {
    // Custom queries can be added here if needed

    @Query("{'name':  ?0}")
    Optional<CaRole> findByName(String name);
}