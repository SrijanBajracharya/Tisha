package com.gemsansar.tisha.authentication.service.impl;

import com.gemsansar.tisha.authentication.service.SessionService;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
class DefaultSessionService implements SessionService {

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || principal.equals("anonymousUser")) {
            throw new UseCaseException("User is not logged in.");
        }
        return (User) principal;
    }

}
