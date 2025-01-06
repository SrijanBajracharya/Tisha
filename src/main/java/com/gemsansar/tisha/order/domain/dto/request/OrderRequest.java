package com.gemsansar.tisha.order.domain.dto.request;

import com.gemsansar.tisha.items.domain.dto.request.ItemCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequest {

    private Instant dueDate;
    private String comment;
    private List<ItemCreateRequest> items;
    private Long customerId;

}
