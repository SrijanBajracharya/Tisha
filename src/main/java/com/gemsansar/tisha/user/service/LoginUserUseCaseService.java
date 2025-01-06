package com.gemsansar.tisha.user.service;


import com.gemsansar.tisha.user.domain.User;

public interface LoginUserUseCaseService {
    User execute(String email, String password);
}
