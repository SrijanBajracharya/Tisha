package com.gemsansar.tisha.order.service.impl;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.persistence.ReadOrderStorageService;
import com.gemsansar.tisha.order.service.GetOrderUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetOrderUseCaseService implements GetOrderUseCaseService {

    private final ReadOrderStorageService readOrderStorageService;

    @Override
    public Order getByOrderId(Long id) {
        return readOrderStorageService.getOrderById(id);
    }
}
