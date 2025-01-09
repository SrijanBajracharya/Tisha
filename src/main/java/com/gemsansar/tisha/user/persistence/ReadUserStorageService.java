package com.gemsansar.tisha.user.persistence;

import com.gemsansar.tisha.user.domain.User;

import java.util.Optional;

public interface ReadUserStorageService {

    User findById(Long userId);
    Optional<User> findByEmail(String email);
}
