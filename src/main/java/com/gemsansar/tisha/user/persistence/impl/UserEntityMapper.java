package com.gemsansar.tisha.user.persistence.impl;

import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
class UserEntityMapper {

    public UserEntity mapToEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .active(true)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .companyId(user.getCompanyId())
                .role(user.getRole())
                .build();
    }

    public User mapToDomain(UserEntity entity){
        return User.builder()
                .id(entity.getId())
                .role(entity.getRole())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .companyId(entity.getCompanyId())
                .build();
    }
}
