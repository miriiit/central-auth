package com.azhs.centralauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "permissions")
@Data
public class CAPermission {
    @Id
    private String id;
    private String name;
}
