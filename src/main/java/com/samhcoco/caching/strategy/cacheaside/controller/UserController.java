package com.samhcoco.caching.strategy.cacheaside.controller;

import com.samhcoco.caching.strategy.cacheaside.model.User;
import com.samhcoco.caching.strategy.cacheaside.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;

    // todo - remove debug logging
    @GetMapping("user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        boolean cacheHit = redisTemplate.hasKey("users::" + id);
        log.info("Redis has users::{} = {}", id, cacheHit);
        log.info("Redis keys: {}", redisTemplate.keys("*"));
        if (cacheHit) {
            log.info("cache HIT: User with ID '{}' will be retrieved from Redis Cache.", id);
        }




        final User user = userService.getById(id);

        return ResponseEntity
                .status(nonNull(user) ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .header("X-Cache", cacheHit ? "HIT" : "MISS")
                .body(nonNull(user) ? user : String.format("User with ID '%s' does not exist.", id));
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        final User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


}
