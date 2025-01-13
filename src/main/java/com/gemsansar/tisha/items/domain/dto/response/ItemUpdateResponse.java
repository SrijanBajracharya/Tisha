package com.gemsansar.tisha.items.domain.dto.response;

import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.order.domain.ItemType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ItemUpdateResponse {

    private Long id;
    private String name;
    private Double purity;
    private Double weight;
    private String comment;
    private ItemStatus status;
    private BigDecimal total;
    private BigDecimal rate;
    private ItemType itemType;
}
