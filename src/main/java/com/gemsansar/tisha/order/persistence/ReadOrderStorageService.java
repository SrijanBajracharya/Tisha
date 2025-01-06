package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;

import java.util.List;

public interface ReadOrderStorageService {

    Order getOrderById(Long id);

    List<Order> fetchAll();

    List<Order> getOrderByUserId(Long userId);
}
