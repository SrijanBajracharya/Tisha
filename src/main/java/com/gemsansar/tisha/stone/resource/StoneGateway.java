package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.domain.dto.request.StoneUpdateRequest;
import com.gemsansar.tisha.stone.domain.dto.response.StoneResponse;
import com.gemsansar.tisha.stone.service.GetStoneByIdUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneByItemIdUseCaseService;
import com.gemsansar.tisha.stone.service.UpdateStoneUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneGateway {

    private final GetStoneByIdUseCaseService getStoneByIdUseCaseService;
    private final StoneDomainMapper stoneDomainMapper;
    private final UpdateStoneUseCaseService updateStoneUseCaseService;
    private final GetStoneByItemIdUseCaseService getStoneByItemIdUseCaseService;

    public StoneResponse update(Long id, StoneUpdateRequest stoneUpdateRequest){
        Stone stone = getStoneByIdUseCaseService.execute(id);
        Stone savedStone = updateStoneUseCaseService.execute(stoneDomainMapper.mapToDomain(stone, stoneUpdateRequest));
        return stoneDomainMapper.mapToResponse(savedStone);
    }

    public List<StoneResponse> getByItemId(Long itemId){
        List<Stone> stones = getStoneByItemIdUseCaseService.execute(itemId);
        return stoneDomainMapper.mapToResponses(stones);
    }
}
