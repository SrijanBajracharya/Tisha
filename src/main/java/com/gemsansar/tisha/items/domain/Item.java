package com.gemsansar.tisha.items.domain;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.order.domain.ItemType;
import com.gemsansar.tisha.stone.domain.Stone;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class Item {

    private Long id;
    private BigDecimal rate;
    private String name;
    private Double purity;
    private Double weight;
    private Instant dueDate;
    private ItemStatus status;
    private String comment;
    private Cost cost;
    private Instant lastModifiedDate;
    private Long lastModifiedBy;
    private List<Stone> stones;
    private ItemType itemType;
    private Long createdBy;
}
