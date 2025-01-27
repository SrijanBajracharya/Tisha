package com.gemsansar.tisha.items.domain.dto.request;

import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.order.domain.ItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ItemUpdateRequest {

    private String name;
    private Double purity;
    private Double weight;
    private String comment;
    private ItemStatus status;
    private Instant dueDate;
    private ItemType itemType;
    private boolean active;
}
