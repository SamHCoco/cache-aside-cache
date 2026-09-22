package com.samhcoco.caching.strategy.cacheaside.service;

import com.samhcoco.caching.strategy.cacheaside.model.User;
import com.samhcoco.caching.strategy.cacheaside.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(long id) {
        return userRepository.findById(id);
    }
}
