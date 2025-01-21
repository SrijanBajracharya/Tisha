package com.gemsansar.tisha.items.domain.dto.request;

import com.gemsansar.tisha.cost.domain.request.CostUpdateRequest;
import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.stone.domain.dto.request.StoneUpdateRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ItemUpdateRequest {

    private Long id;
    private String name;
    private Double purity;
    private Double weight;
    private String comment;
    private ItemStatus status;
    private List<StoneUpdateRequest> stones;
    private CostUpdateRequest cost;
    private boolean active;
}
