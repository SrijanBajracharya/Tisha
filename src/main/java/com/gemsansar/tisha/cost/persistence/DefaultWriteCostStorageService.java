package com.gemsansar.tisha.cost.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteCostStorageService implements WriteCostStorageService{

    private final CostEntityRepository costEntityRepository;
    private final CostEntityMapper costEntityMapper;

    @Override
    public Cost update(Cost cost) {
        CostEntity entity = costEntityMapper.mapToEntity(cost);
        return costEntityMapper.mapToDomain(costEntityRepository.save(entity));
    }
}
