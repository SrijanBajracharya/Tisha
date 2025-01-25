package com.gemsansar.tisha.user.domain;

import com.gemsansar.tisha.platform.enums.AppRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private AppRole role;
    private Long companyId;

}
