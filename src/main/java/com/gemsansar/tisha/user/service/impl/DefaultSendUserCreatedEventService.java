package com.gemsansar.tisha.user.service.impl;

import com.gemsansar.tisha.user.persistence.ReadUserStorageService;
import com.gemsansar.tisha.user.service.SendUserCreatedEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultSendUserCreatedEventService implements SendUserCreatedEventService {

    private final ReadUserStorageService readUserStorageService;

    @Override
    public void send(Long userId) {
        readUserStorageService.findById(userId);
        // send email.
    }
}
