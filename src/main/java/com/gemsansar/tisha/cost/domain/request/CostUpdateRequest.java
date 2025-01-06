package com.gemsansar.tisha.cost.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CostUpdateRequest {

    private Long id;
    private BigDecimal jyala;
    private Double jartiQuantity;
}
