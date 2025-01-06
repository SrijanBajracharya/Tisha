package com.gemsansar.tisha.cost.service.impl;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.persistence.ReadCostStorageService;
import com.gemsansar.tisha.cost.service.GetCostByIdUseCaseService;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetCostByIdUseCaseService implements GetCostByIdUseCaseService {

    private final ReadCostStorageService readCostStorageService;

    @Override
    public Cost execute(Long id) {
        return readCostStorageService.findById(id).orElseThrow(() -> new UseCaseException("Cost not found for id:" + id));
    }
}
