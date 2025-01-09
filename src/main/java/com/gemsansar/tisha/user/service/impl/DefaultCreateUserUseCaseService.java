package com.gemsansar.tisha.user.service.impl;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.persistence.ReadUserStorageService;
import com.gemsansar.tisha.user.persistence.WriteUserStorageService;
import com.gemsansar.tisha.user.service.CreateUserUseCaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultCreateUserUseCaseService implements CreateUserUseCaseService {

    private final WriteUserStorageService writeUserStorageService;
    private final ReadUserStorageService readUserStorageService;

    @Transactional
    @Override
    public User execute(User user) {
        Optional<User> existingUser = readUserStorageService.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new UseCaseException("User with email already exists.");
        }
        return writeUserStorageService.create(user);
    }
}
