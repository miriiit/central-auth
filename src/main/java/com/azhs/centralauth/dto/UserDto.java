package com.azhs.centralauth.dto;

import com.azhs.centralauth.entities.CaRole;
import com.azhs.centralauth.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private String name;
    private String email;
    private CaRole caRole;

    public static UserDto mapToDto(User user) {
        UserDto userDto = UserDto.builder()
                .name(user.getFirstName()+ " "+user.getLastName())
                .email(user.getEmail())
                .caRole(user.getCaRole())
                .build();
        return userDto;
    }
}
