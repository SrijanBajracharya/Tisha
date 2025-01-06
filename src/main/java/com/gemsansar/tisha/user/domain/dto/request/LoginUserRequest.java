package com.gemsansar.tisha.user.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
