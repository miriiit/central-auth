package com.azhs.centralauth.repositories;

import com.azhs.centralauth.entities.CaPermission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CaPermissionRepository  extends MongoRepository<CaPermission, String> {
    // Custom queries can be added here if needed

    @Query("{'name': { $in: ?0 }}")
    Optional<List<CaPermission>> findByName(List<String> names);
}