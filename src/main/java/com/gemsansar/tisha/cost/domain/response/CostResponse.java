package com.gemsansar.tisha.cost.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CostResponse {

    private Long id;
    private Long itemId;
    private BigDecimal jyala;
    private Double jartiQuantity;

}
