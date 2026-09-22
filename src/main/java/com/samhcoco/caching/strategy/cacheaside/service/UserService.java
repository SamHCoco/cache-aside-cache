package com.samhcoco.caching.strategy.cacheaside.service;

import com.samhcoco.caching.strategy.cacheaside.model.User;

public interface UserService {
    User getById(long id);
    User create(User user);
}
