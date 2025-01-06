package com.gemsansar.tisha.cost.persistence;

import com.gemsansar.tisha.cost.domain.Cost;

import java.util.Optional;

public interface ReadCostStorageService {

    Optional<Cost> findById(Long id);
}
