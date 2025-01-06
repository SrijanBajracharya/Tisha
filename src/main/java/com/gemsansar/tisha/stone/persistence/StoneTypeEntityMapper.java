package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoneTypeEntityMapper {

    public List<StoneType> mapToDomains(List<StoneTypeEntity> entities){
        return entities.stream().map(this::mapToDomain).toList();
    }

    public StoneType mapToDomain(StoneTypeEntity entity){
        return StoneType.builder()
                .id(entity.getId())
                .name(entity.getName())
                .caret(entity.getCaret())
                .type(entity.getType())
                .build();
    }

    public StoneTypeEntity mapToEntity(StoneType stoneType){
        return StoneTypeEntity.builder()
                .id(stoneType.getId())
                .name(stoneType.getName())
                .type(stoneType.getType())
                .caret(stoneType.getCaret())
                .build();
    }
}
