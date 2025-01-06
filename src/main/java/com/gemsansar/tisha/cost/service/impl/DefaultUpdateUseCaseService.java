package com.gemsansar.tisha.cost.service.impl;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.persistence.WriteCostStorageService;
import com.gemsansar.tisha.cost.service.UpdateCostUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultUpdateUseCaseService implements UpdateCostUseCaseService {

    private final WriteCostStorageService writeCostStorageService;

    @Override
    public Cost execute(Cost cost) {
        return writeCostStorageService.update(cost);
    }
}