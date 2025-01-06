package com.gemsansar.tisha.user.persistence;

import com.gemsansar.tisha.user.domain.User;

public interface ReadUserStorageService {

    User findById(Long userId);
    User findByEmail(String email);
}
