package com.gemsansar.tisha.user.domain.dto.response;

import com.gemsansar.tisha.platform.enums.AppRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponse {
    private String accessToken;
    private AppRole role;
}
