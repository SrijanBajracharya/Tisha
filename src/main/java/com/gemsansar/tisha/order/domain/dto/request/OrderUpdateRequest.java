package com.gemsansar.tisha.order.domain.dto.request;

import com.gemsansar.tisha.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class OrderUpdateRequest {

    private Instant dueDate;
    private String comment;
    private Long customerId;
    private OrderStatus status;

}
