package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.entity.OrderEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultReadOrderStorageService implements ReadOrderStorageService {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order getOrderById(Long id) {
        OrderEntity order = orderEntityRepository.findById(id)
                .orElseThrow(() -> new UseCaseException("Order not found with id: " + id));
        return orderEntityMapper.mapToDomain(order);
    }

    @Override
    public Page<Order> fetchAll(Pageable pageable) {
        Page<OrderEntity> orderEntities = orderEntityRepository.findAll(pageable);
        return orderEntities.map(orderEntityMapper::mapToDomain);
    }

    @Override
    public Page<Order> getOrderByUserId(Long userId, Pageable pageable) {
        return orderEntityRepository.findByCustomerId(userId, pageable).map(orderEntityMapper::mapToDomain);
    }
}
