package com.azhs.centralauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Permission;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Document(collection = "roles")
public class CARole {
    @Id
    private String id;
    private String name;

    @DBRef(lazy=true)
    private User user;

    @DBRef
    private List<CAPermission> caPermissions;
}