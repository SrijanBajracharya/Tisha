package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;

public interface WriteOrderStorageService {

    Order create(Order order);

    Order update(Order order);
}
