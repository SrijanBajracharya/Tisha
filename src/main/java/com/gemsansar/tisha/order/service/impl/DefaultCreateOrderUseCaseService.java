package com.gemsansar.tisha.order.service.impl;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.persistence.WriteOrderStorageService;
import com.gemsansar.tisha.order.service.CreateOrderUseCaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultCreateOrderUseCaseService implements CreateOrderUseCaseService {

    private final WriteOrderStorageService writeOrderStorageService;

    @Transactional
    @Override
    public Order handle(Order order) {
        return writeOrderStorageService.create(order);
    }
}
