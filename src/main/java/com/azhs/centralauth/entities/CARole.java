package com.azhs.centralauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    @DocumentReference(lazy=true)
    private User user;

    @ReadOnlyProperty
    @DocumentReference(lookup="{'caRole':?#{#self._id} }")
    private List<CAPermission> caPermissions;
}