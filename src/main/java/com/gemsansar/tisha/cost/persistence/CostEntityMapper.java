package com.gemsansar.tisha.cost.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.items.persistence.ItemEntityRepository;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CostEntityMapper {

    private final ItemEntityRepository itemEntityRepository;
    private final CostEntityRepository costEntityRepository;

    public CostEntity mapToEntity(Cost cost){
        ItemsEntity item = itemEntityRepository.findById(cost.getItemId()).orElseThrow(()-> new UseCaseException("Item not found with id:" + cost.getItemId()));
        CostEntity costEntity = costEntityRepository.findById(cost.getId()).orElseThrow(()-> new UseCaseException("Cost not found with id:" + cost.getId()));
        costEntity.setJartiQuantity(cost.getJartiQuantity());
        costEntity.setJyala(cost.getJyala());
        return costEntity;
    }

    public Cost mapToDomain(CostEntity entity){
        return Cost.builder()
                .id(entity.getId())
                .itemId(entity.getItem().getId())
                .jyala(entity.getJyala())
                .jartiQuantity(entity.getJartiQuantity())
                .build();
    }
}
