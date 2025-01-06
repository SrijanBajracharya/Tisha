package com.gemsansar.tisha.cost.resource;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.domain.request.CostUpdateRequest;
import com.gemsansar.tisha.cost.domain.response.CostUpdateResponse;
import org.springframework.stereotype.Component;

@Component
class CostDomainMapper {

    public Cost mapToDomain(Cost costInDb, CostUpdateRequest costUpdateRequest){
        return Cost.builder()
                .id(costInDb.getId())
                .itemId(costInDb.getItemId())
                .jyala(costUpdateRequest.getJyala())
                .jartiQuantity(costUpdateRequest.getJartiQuantity())
                .build();
    }

    public CostUpdateResponse mapToResponse(Cost cost){
        return CostUpdateResponse.builder()
                .id(cost.getId())
                .itemId(cost.getItemId())
                .jartiQuantity(cost.getJartiQuantity())
                .jyala(cost.getJyala())
                .build();
    }
}
