package com.azhs.centralauth.service;

import com.azhs.centralauth.dto.CreateUserRequest;
import com.azhs.centralauth.dto.UserDto;
import com.azhs.centralauth.entities.CaPermission;
import com.azhs.centralauth.entities.User;
import com.azhs.centralauth.enums.ECaPermission;
import com.azhs.centralauth.repositories.CaPermissionRepository;
import com.azhs.centralauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CaPermissionRepository caPermissionRepository;

    @Autowired
    public UserService(UserRepository userRepository, CaPermissionRepository caPermissionRepository) {
        this.userRepository = userRepository;
        this.caPermissionRepository = caPermissionRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(CreateUserRequest user) {

        // Fetch Roles Based on createuserrequest names list
        // add this roles to user
        // First ftech roles and check permissions commings.

        return null; // userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        return null; // Handle the case when the user with the given ID is not found
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public List<CaPermission> getAllPermission() {
        return caPermissionRepository.findAll();
    }

    public Optional<CaPermission> getCaPermission(String name) {
        return caPermissionRepository.findByName(name);
    }
}