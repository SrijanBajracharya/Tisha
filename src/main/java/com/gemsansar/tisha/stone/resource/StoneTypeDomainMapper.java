package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import org.springframework.stereotype.Component;

@Component
class StoneTypeDomainMapper {

    public StoneType mapToDomain(StoneTypeRequest request){
        return StoneType.builder()
                .name(request.getName())
                .caret(request.getCaret())
                .type(request.getType())
                .build();
    }

    public StoneType mapToDomain(StoneType stoneType, StoneTypeUpdateRequest request){
        return StoneType.builder()
                .id(stoneType.getId())
                .name(request.getName())
                .type(request.getType())
                .caret(request.getCaret())
                .build();
    }
}
