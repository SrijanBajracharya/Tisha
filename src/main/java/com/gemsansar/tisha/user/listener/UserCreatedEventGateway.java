package com.gemsansar.tisha.user.listener;

import com.gemsansar.tisha.user.service.SendUserCreatedEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserCreatedEventGateway {

    private final SendUserCreatedEventService sendUserCreatedEventService;

    public void create(Long userId){
        sendUserCreatedEventService.send(userId);
    }
}
