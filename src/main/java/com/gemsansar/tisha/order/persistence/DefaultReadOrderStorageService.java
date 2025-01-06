package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.entity.OrderEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Order> fetchAll() {
        return orderEntityMapper.mapToDomains(orderEntityRepository.findAll());
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        return orderEntityMapper.mapToDomains(orderEntityRepository.findByCustomerId(userId));
    }
}
