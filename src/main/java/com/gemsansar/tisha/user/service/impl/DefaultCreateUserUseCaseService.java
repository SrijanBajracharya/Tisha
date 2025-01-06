package com.gemsansar.tisha.user.service.impl;

import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.persistence.WriteUserStorageService;
import com.gemsansar.tisha.user.service.CreateUserUseCaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultCreateUserUseCaseService implements CreateUserUseCaseService {

    private final WriteUserStorageService writeUserStorageService;

    @Transactional
    @Override
    public User execute(User user) {
        // password encrypt
        return writeUserStorageService.create(user);
    }
}
