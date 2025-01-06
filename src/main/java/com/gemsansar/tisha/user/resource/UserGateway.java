package com.gemsansar.tisha.user.resource;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.domain.dto.request.CreateUserRequest;
import com.gemsansar.tisha.user.domain.dto.request.LoginUserRequest;
import com.gemsansar.tisha.user.domain.dto.response.CreateUserResponse;
import com.gemsansar.tisha.user.domain.dto.response.LoginUserResponse;
import com.gemsansar.tisha.user.service.CreateUserUseCaseService;
import com.gemsansar.tisha.user.service.LoginUserUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserGateway {

    private final UserDomainMapper userDomainMapper;
    private final CreateUserUseCaseService createUserUseCaseService;
    private final LoginUserUseCaseService loginUserUseCaseService;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        if(!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            throw new UseCaseException("Password and Confirm Password mismatch.");
        }
        User user = userDomainMapper.mapToDomain(createUserRequest);
        User savedUser = createUserUseCaseService.execute(user);
        return userDomainMapper.mapToResponse(savedUser);
    }

    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest){
        User user = loginUserUseCaseService.execute(loginUserRequest.getEmail(), loginUserRequest.getPassword());
        return userDomainMapper.mapToLoginUserResponse(user);
    }
}
