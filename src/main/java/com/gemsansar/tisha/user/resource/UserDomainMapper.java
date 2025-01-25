package com.gemsansar.tisha.user.resource;

import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.domain.dto.request.CreateUserRequest;
import com.gemsansar.tisha.user.domain.dto.response.CreateUserResponse;
import com.gemsansar.tisha.user.domain.dto.response.LoginUserResponse;
import org.springframework.stereotype.Component;

@Component
class UserDomainMapper {

    public User mapToDomain(CreateUserRequest createUserRequest, String encodedPassword){
        return User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .password(encodedPassword)
                .email(createUserRequest.getEmail())
                .role(createUserRequest.getRole())
                .companyId(createUserRequest.getCompanyId())
                .address(createUserRequest.getAddress())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .build();
    }

    public CreateUserResponse mapToResponse(User user){
        return CreateUserResponse.builder()
                .message(user != null ? "User successfully created." : "Error creating user.")
                .status("Ok")
                .build();
    }

    public LoginUserResponse mapToLoginUserResponse(User user){
        return LoginUserResponse.builder()
                .accessToken("1")
                .role(user.getRole())
                .build();
    }
}
