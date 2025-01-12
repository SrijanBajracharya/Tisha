package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.domain.dto.request.StoneUpdateRequest;
import com.gemsansar.tisha.stone.domain.dto.response.StoneResponse;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneDomainMapper {

    private final GetStoneTypeByIdUseCaseService getStoneTypeByIdUseCaseService;

    public Stone mapToDomain(Stone stoneInDb, StoneUpdateRequest request){
        return Stone.builder()
                .id(stoneInDb.getId())
                .itemId(stoneInDb.getItemId())
                .price(request.getPrice())
                .stoneTypeId(request.getStoneTypeId())
                .quantity(request.getQuantity())
                .build();
    }

    public List<StoneResponse> mapToResponses(List<Stone> stones){
        return stones.stream().map(this::mapToResponse).toList();
    }

    public StoneResponse mapToResponse(Stone stone){
        return StoneResponse.builder()
                .id(stone.getId())
                .itemId(stone.getItemId())
                .price(stone.getPrice())
                .stoneType(getStoneTypeByIdUseCaseService.execute(stone.getStoneTypeId()))
                .quantity(stone.getQuantity())
                .build();
    }
}
