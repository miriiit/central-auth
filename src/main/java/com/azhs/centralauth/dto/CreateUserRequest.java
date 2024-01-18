package com.azhs.centralauth.dto;

import com.azhs.centralauth.entities.CaRole;
import com.azhs.centralauth.enums.ECaRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private List<ECaRole> eCaRoles;
}
