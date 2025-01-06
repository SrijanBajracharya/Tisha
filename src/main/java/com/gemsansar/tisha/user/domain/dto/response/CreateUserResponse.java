package com.gemsansar.tisha.user.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateUserResponse {

    private String message;
    private String status;
}
