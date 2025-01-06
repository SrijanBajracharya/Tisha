package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.items.persistence.ItemEntityRepository;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneEntityMapper {

    private final ItemEntityRepository itemEntityRepository;

    public StoneEntity mapToEntity(Stone stone){
        ItemsEntity item = itemEntityRepository.findById(stone.getItemId()).orElseThrow(()-> new UseCaseException("Item not found with id: " + stone.getItemId()));
        return StoneEntity.builder()
                .id(stone.getId())
                .type(mapToStoneTypeEntity(stone.getStoneType()))
                .price(stone.getPrice())
                .quantity(stone.getQuantity())
                .item(item)
                .build();
    }

    public List<Stone> mapToDomains(List<StoneEntity> stoneEntities){
        return stoneEntities.stream().map(this::mapToDomain).toList();
    }

    public Stone mapToDomain(StoneEntity entity){
        return Stone.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .stoneType(mapToStoneType(entity.getType()))
                .quantity(entity.getQuantity())
                .itemId(entity.getItem().getId())
                .build();
    }

    public StoneType mapToStoneType(StoneTypeEntity entity){
        return StoneType.builder()
                .id(entity.getId())
                .type(entity.getType())
                .name(entity.getName())
                .caret(entity.getCaret())
                .build();
    }

    private StoneTypeEntity mapToStoneTypeEntity(StoneType stoneType){
        return StoneTypeEntity.builder()
                .id(stoneType.getId())
                .type(stoneType.getType())
                .name(stoneType.getName())
                .caret(stoneType.getCaret())
                .build();
    }
}
