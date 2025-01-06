package com.gemsansar.tisha.user.resource;

import com.gemsansar.tisha.user.domain.dto.request.CreateUserRequest;
import com.gemsansar.tisha.user.domain.dto.request.LoginUserRequest;
import com.gemsansar.tisha.user.domain.dto.response.CreateUserResponse;
import com.gemsansar.tisha.user.domain.dto.response.LoginUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserGateway userGateway;

    @PostMapping
    public ResponseEntity<CreateUserResponse> create(@RequestBody @Valid CreateUserRequest createUserRequest){
        CreateUserResponse response = userGateway.createUser(createUserRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest loginUserRequest){
        return ResponseEntity.ok(userGateway.loginUser(loginUserRequest));
    }
}
