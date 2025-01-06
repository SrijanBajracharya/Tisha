package com.gemsansar.tisha.cost.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultReadCostStorageService implements ReadCostStorageService{

    private final CostEntityRepository costEntityRepository;
    private final CostEntityMapper costEntityMapper;

    @Override
    public Optional<Cost> findById(Long id) {
        return costEntityRepository.findById(id).map(costEntityMapper::mapToDomain);
    }
}
