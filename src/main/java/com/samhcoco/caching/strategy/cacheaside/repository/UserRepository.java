package com.samhcoco.caching.strategy.cacheaside.repository;

import com.samhcoco.caching.strategy.cacheaside.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
