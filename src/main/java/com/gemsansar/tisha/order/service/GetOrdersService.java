package com.gemsansar.tisha.order.service;

import com.gemsansar.tisha.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetOrdersService {

    Page<Order> getOrders(Pageable pageable);

    Page<Order> getOrdersByUserId(Long userId, Pageable pageable);
}
