package com.samhcoco.caching.strategy.cacheaside.controller;

import com.samhcoco.caching.strategy.cacheaside.model.User;
import com.samhcoco.caching.strategy.cacheaside.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        final User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        final User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


}
