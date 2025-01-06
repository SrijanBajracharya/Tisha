package com.gemsansar.tisha.order.domain.dto.request;

import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class OrderUpdateRequest {

    private Instant dueDate;
    private String comment;
    private List<ItemUpdateRequest> items;
    private Long customerId;
    private OrderStatus status;

}
