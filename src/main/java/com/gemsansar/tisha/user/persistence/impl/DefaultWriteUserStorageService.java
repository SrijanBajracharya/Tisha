package com.gemsansar.tisha.user.persistence.impl;

import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.entity.UserEntity;
import com.gemsansar.tisha.user.event.UserCreatedEvent;
import com.gemsansar.tisha.user.persistence.UserEntityRepository;
import com.gemsansar.tisha.user.persistence.WriteUserStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteUserStorageService implements WriteUserStorageService {

    private final UserEntityMapper userEntityMapper;
    private final UserEntityRepository userEntityRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public User create(User user) {
        UserEntity savedUserEntity = userEntityRepository.save(userEntityMapper.mapToEntity(user));
        User savedUser = userEntityMapper.mapToDomain(savedUserEntity);
        applicationEventPublisher.publishEvent(new UserCreatedEvent(savedUser.getId()));
        return savedUser;
    }
}
