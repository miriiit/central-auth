package com.azhs.centralauth.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "users")
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;

    @DBRef(lazy = true) //
    private CaRole caRole;
}
