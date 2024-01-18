package com.azhs.centralauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "permissions")
@Data
public class CaPermission {
    @Id
    private String id;
    private String name;
}
