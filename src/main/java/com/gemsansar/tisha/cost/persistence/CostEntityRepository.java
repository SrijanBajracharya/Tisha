package com.gemsansar.tisha.cost.persistence;

import com.gemsansar.tisha.cost.entities.CostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostEntityRepository extends JpaRepository<CostEntity, Long> {
}
