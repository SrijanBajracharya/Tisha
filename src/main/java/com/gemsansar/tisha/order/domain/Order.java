package com.gemsansar.tisha.order.domain;

import com.gemsansar.tisha.items.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class Order {

    private Long id;
    private Instant dueDate;
    private OrderStatus status;
    private String comment;
    private List<Item> items;
    private Instant lastModifiedDate;
    private Long lastModifiedBy;
    private Long createBy;
    private Long customerId;

}
