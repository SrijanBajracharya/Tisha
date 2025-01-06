package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteOrderStorageService implements WriteOrderStorageService {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order create(Order order) {
        OrderEntity orderToSave = orderEntityMapper.mapToEntity(order);
        OrderEntity savedOrder = orderEntityRepository.save(orderToSave);
        return orderEntityMapper.mapToDomain(savedOrder);
    }

    @Override
    public Order update(Order order) {
        OrderEntity orderToSave = orderEntityMapper.mapToEntity(order);
        OrderEntity savedOrder = orderEntityRepository.save(orderToSave);
        return orderEntityMapper.mapToDomain(savedOrder);
    }

}
