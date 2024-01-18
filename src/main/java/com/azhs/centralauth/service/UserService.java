package com.azhs.centralauth.service;

import com.azhs.centralauth.dto.CreateUserRequest;
import com.azhs.centralauth.dto.UserDto;
import com.azhs.centralauth.entities.CaPermission;
import com.azhs.centralauth.entities.CaRole;
import com.azhs.centralauth.entities.User;
import com.azhs.centralauth.enums.ECaPermission;
import com.azhs.centralauth.enums.ECaRole;
import com.azhs.centralauth.repositories.CaPermissionRepository;
import com.azhs.centralauth.repositories.CaRoleRepository;
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

    private final CaRoleRepository caRoleRepository;

    @Autowired
    public UserService(UserRepository userRepository, CaPermissionRepository caPermissionRepository, CaRoleRepository caRoleRepository) {
        this.userRepository = userRepository;
        this.caPermissionRepository = caPermissionRepository;
        this.caRoleRepository = caRoleRepository;
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

    public User createUser(CreateUserRequest req) {

        String roleName = Optional.of(req.getECaRole()).orElse(ECaRole.USER).name();
        CaRole role = getCaRole(roleName).get();

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .caRole(role)
                .build();

        return userRepository.save(user);
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

    public Optional<List<CaPermission>> getCaPermission(List<String> names) {
        return caPermissionRepository.findByName(names);
    }

    public Optional<CaRole> getCaRole(String name) {
        return caRoleRepository.findByName(name);
    }
}