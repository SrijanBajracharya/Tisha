package com.gemsansar.tisha.items.domain.dto.response;

import com.gemsansar.tisha.cost.domain.response.CostResponse;
import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.order.domain.ItemType;
import com.gemsansar.tisha.stone.domain.dto.response.StoneResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ItemResponse {

    private Long id;
    private String name;
    private Double purity;
    private Double weight;
    private String comment;
    private ItemStatus status;
    private BigDecimal total;
    private List<StoneResponse> stones;
    private BigDecimal rate;
    private ItemType itemType;
    private CostResponse costResponse;
}
