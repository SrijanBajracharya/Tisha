package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.order.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findByCustomerId(Long customerId, Pageable pageable);
}
