package com.gemsansar.tisha.cost.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CostUpdateResponse {

    private Long id;
    private BigDecimal jyala;
    private Double jartiQuantity;
    private Long itemId;
}
