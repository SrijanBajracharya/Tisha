package com.gemsansar.tisha.order.service.impl;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.persistence.ReadOrderStorageService;
import com.gemsansar.tisha.order.service.GetOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetOrdersService implements GetOrdersService {

    private final ReadOrderStorageService readOrderStorageService;

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return readOrderStorageService.fetchAll(pageable);
    }

    @Override
    public Page<Order> getOrdersByUserId(Long userId, Pageable pageable) {
        return readOrderStorageService.getOrderByUserId(userId, pageable);
    }
}
