package com.gemsansar.tisha.user.service.impl;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.persistence.ReadUserStorageService;
import com.gemsansar.tisha.user.service.LoginUserUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultLoginUserUseCaseService implements LoginUserUseCaseService {

    private final ReadUserStorageService readUserStorageService;

    @Override
    public User execute(String email, String password) {
        User user = readUserStorageService.findByEmail(email);
        if(!user.getPassword().equals(password)){
            throw new UseCaseException("Invalid Password");
        }
        return user;
    }
}
