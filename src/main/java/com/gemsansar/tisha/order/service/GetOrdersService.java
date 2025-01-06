package com.gemsansar.tisha.order.service;

import com.gemsansar.tisha.order.domain.Order;

import java.util.List;

public interface GetOrdersService {

    List<Order> getOrders();

    List<Order> getOrdersByUserId(Long userId);
}
