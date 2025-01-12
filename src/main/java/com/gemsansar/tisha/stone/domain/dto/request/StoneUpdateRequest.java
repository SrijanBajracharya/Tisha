package com.gemsansar.tisha.stone.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class StoneUpdateRequest {

    private BigDecimal price;
    private Integer quantity;
    private Long stoneTypeId;
    private Long itemId;
}
