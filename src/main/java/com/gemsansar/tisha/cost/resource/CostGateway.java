package com.gemsansar.tisha.cost.resource;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.domain.request.CostUpdateRequest;
import com.gemsansar.tisha.cost.domain.response.CostUpdateResponse;
import com.gemsansar.tisha.cost.service.GetCostByIdUseCaseService;
import com.gemsansar.tisha.cost.service.UpdateCostUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostGateway {

    private final GetCostByIdUseCaseService getCostByIdUseCaseService;
    private final CostDomainMapper costDomainMapper;
    private final UpdateCostUseCaseService updateCostUseCaseService;

    public CostUpdateResponse update(Long id, CostUpdateRequest costUpdateRequest){
        Cost costInDb = getCostByIdUseCaseService.execute(id);
        Cost costToSave = costDomainMapper.mapToDomain(costInDb, costUpdateRequest);
        return costDomainMapper.mapToResponse(updateCostUseCaseService.execute(costToSave));
    }
}
