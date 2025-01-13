package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneEntityMapper {

    private final StoneTypeEntityRepository stoneTypeEntityRepository;
    private final StoneEntityRepository stoneEntityRepository;

    public StoneEntity mapToEntity(Stone stone){

        StoneEntity stoneEntity = stoneEntityRepository.findById(stone.getId()).orElseThrow(()-> new UseCaseException("Stone not found with id: " + stone.getId()));
        stoneEntity.setQuantity(stone.getQuantity());
        stoneEntity.setType(stoneTypeEntityRepository.findById(stone.getStoneTypeId()).orElseThrow(()-> new UseCaseException("Stone type not found with id:" + stone.getStoneTypeId())));
        stoneEntity.setPrice(stone.getPrice());
        return stoneEntity;
    }

    public List<Stone> mapToDomains(List<StoneEntity> stoneEntities){
        return stoneEntities.stream().map(this::mapToDomain).toList();
    }

    public Stone mapToDomain(StoneEntity entity){
        return Stone.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .stoneTypeId(entity.getType().getId())
                .quantity(entity.getQuantity())
                .itemId(entity.getItem().getId())
                .build();
    }

}
