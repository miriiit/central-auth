package com.azhs.centralauth.dto;

import com.azhs.centralauth.enums.ECaRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private ECaRole eCaRole;
}
