package com.gemsansar.tisha.order.service;

import com.gemsansar.tisha.order.domain.Order;

public interface GetOrderUseCaseService {

    Order getByOrderId(Long id);
}
