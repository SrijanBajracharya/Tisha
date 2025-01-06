package com.gemsansar.tisha.stone.domain.dto.request;

import com.gemsansar.tisha.stone.domain.StoneType;
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
    private StoneType stoneType;
    private Long itemId;
}