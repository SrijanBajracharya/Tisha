package com.gemsansar.tisha.stone.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class Stone {

    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Long stoneTypeId;
    private Long itemId;

}
