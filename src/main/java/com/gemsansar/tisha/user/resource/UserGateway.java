package com.gemsansar.tisha.user.resource;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.domain.dto.request.CreateUserRequest;
import com.gemsansar.tisha.user.domain.dto.request.LoginUserRequest;
import com.gemsansar.tisha.user.domain.dto.response.CreateUserResponse;
import com.gemsansar.tisha.user.domain.dto.response.LoginUserResponse;
import com.gemsansar.tisha.user.service.CreateUserUseCaseService;
import com.gemsansar.tisha.user.service.LoginUserUseCaseService;
import com.gemsansar.tisha.user.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserGateway {

    private final UserDomainMapper userDomainMapper;
    private final CreateUserUseCaseService createUserUseCaseService;
    private final LoginUserUseCaseService loginUserUseCaseService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        if(!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            throw new UseCaseException("Password and Confirm Password mismatch.");
        }
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        User user = userDomainMapper.mapToDomain(createUserRequest, encodedPassword);
        User savedUser = createUserUseCaseService.execute(user);
        return userDomainMapper.mapToResponse(savedUser);
    }

    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest){
        User user = loginUserUseCaseService.execute(loginUserRequest.getEmail(), loginUserRequest.getPassword());
        if (passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getId(), user.getEmail());

            return LoginUserResponse.builder()
                    .email(user.getEmail())
                    .role(user.getRole())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .id(user.getId())
                    .build();
        }
        throw new UseCaseException("Password doesn't match.");

    }
}
