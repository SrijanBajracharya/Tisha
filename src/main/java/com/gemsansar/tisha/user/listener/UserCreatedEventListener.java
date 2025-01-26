package com.gemsansar.tisha.user.listener;

import com.gemsansar.tisha.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreatedEventListener {

    private final UserCreatedEventGateway userCreatedEventGateway;

    @EventListener
    public void handle(UserCreatedEvent event){
        userCreatedEventGateway.create(event.userId());
    }
}
