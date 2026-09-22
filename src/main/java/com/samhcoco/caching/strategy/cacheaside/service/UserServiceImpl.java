package com.samhcoco.caching.strategy.cacheaside.service;

import com.samhcoco.caching.strategy.cacheaside.model.User;
import com.samhcoco.caching.strategy.cacheaside.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(@NonNull User user) {
        if (userRepository.existsById(user.getId())) {
            String error = String.format("FAILED TO CREATE '%s': User with ID '%s' already exists.", user, user.getId());
            log.error(error);
            throw new RuntimeException(error);
        }
        return userRepository.save(user);
    }
}
