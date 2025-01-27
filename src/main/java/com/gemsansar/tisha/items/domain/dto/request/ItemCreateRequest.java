package com.gemsansar.tisha.items.domain.dto.request;

import com.gemsansar.tisha.cost.domain.request.CostCreateRequest;
import com.gemsansar.tisha.order.domain.ItemType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneCreateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
public class ItemCreateRequest {

    private String name;
    private Double purity;
    private Double weight;
    private String comment;
    private List<StoneCreateRequest> stones;
    private BigDecimal rate;
    private ItemType itemType;
    private CostCreateRequest cost;
}
