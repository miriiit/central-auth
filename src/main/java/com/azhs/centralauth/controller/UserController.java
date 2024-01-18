package com.azhs.centralauth.controller;

import com.azhs.centralauth.dto.CreateUserRequest;
import com.azhs.centralauth.dto.UserDto;
import com.azhs.centralauth.entities.CaPermission;
import com.azhs.centralauth.entities.CaRole;
import com.azhs.centralauth.entities.User;
import com.azhs.centralauth.enums.ECaPermission;
import com.azhs.centralauth.enums.ECaRole;
import com.azhs.centralauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest user) {
        User createdUser = userService.createUser(user);
        return createdUser != null ? new ResponseEntity<>(createdUser, HttpStatus.CREATED) :  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/permissions/{eCaPermission}")
    public ResponseEntity<List<CaPermission>>  getCaPermissionByName(@PathVariable List<ECaPermission> eCaPermission) {
        List<String> typeList = eCaPermission != null ? eCaPermission.stream().map(value -> value.name()).collect(Collectors.toList()) : List.of(ECaPermission.VIEW.name());
        List<CaPermission> permissions = userService.getCaPermission(typeList).get();
        return permissions.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :  ResponseEntity.ok(permissions);
    }

    @GetMapping("/roles/{eCaRole}")
    public ResponseEntity<CaRole>  getCaRoleByName(@PathVariable ECaRole eCaRole) {
        String type = (Optional.of(eCaRole).orElse(ECaRole.USER)).name();
        Optional<CaRole> role = userService.getCaRole(type);
        return role.map(value-> new ResponseEntity<CaRole>(value, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}