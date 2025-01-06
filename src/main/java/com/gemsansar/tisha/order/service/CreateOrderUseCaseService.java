package com.gemsansar.tisha.order.service;

import com.gemsansar.tisha.order.domain.Order;

public interface CreateOrderUseCaseService {

    Order handle(Order order);
}
