package com.gemsansar.tisha.order.domain.dto.response;

import com.gemsansar.tisha.items.domain.dto.response.ItemResponse;
import com.gemsansar.tisha.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;
    private Instant dueDate;
    private OrderStatus status;
    private Long customerId;
    private BigDecimal totalPrice;
    private List<ItemResponse> items;
    private String comment;
}
