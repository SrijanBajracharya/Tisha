package com.gemsansar.tisha.order.service.impl;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.persistence.ReadOrderStorageService;
import com.gemsansar.tisha.order.service.GetOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class DefaultGetOrdersService implements GetOrdersService {

    private final ReadOrderStorageService readOrderStorageService;

    @Override
    public List<Order> getOrders() {
        return readOrderStorageService.fetchAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return readOrderStorageService.getOrderByUserId(userId);
    }
}
