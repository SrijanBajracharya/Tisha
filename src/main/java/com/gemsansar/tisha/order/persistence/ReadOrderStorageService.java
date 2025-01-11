package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadOrderStorageService {

    Order getOrderById(Long id);

    Page<Order> fetchAll(Pageable pageable);

    Page<Order> getOrderByUserId(Long userId, Pageable pageable);
}
