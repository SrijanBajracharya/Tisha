package com.gemsansar.tisha.user.persistence.impl;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import com.gemsansar.tisha.user.entity.UserEntity;
import com.gemsansar.tisha.user.persistence.ReadUserStorageService;
import com.gemsansar.tisha.user.persistence.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultReadUserStorageService implements ReadUserStorageService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User findById(Long userId) {
         UserEntity entity = userEntityRepository.findById(userId).orElseThrow(() ->
          new UseCaseException("User not found with id" + userId));
         return userEntityMapper.mapToDomain(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entity = userEntityRepository.findByEmail(email);
        return entity.map(userEntityMapper::mapToDomain);
    }
}
