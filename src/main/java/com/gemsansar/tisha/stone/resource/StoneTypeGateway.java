package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import com.gemsansar.tisha.stone.service.CreateStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.UpdateStoneTypeUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneTypeGateway {

    private final GetStoneTypeUseCaseService getStoneTypeUseCaseService;
    private final StoneTypeDomainMapper stoneTypeDomainMapper;
    private final CreateStoneTypeUseCaseService createStoneTypeUseCaseService;
    private final UpdateStoneTypeUseCaseService updateStoneTypeUseCaseService;
    private final GetStoneTypeByIdUseCaseService getStoneTypeByIdUseCaseService;

    public List<StoneType> fetchAll(){
        return getStoneTypeUseCaseService.fetchAll();
    }

    public StoneType create(StoneTypeRequest stoneTypeRequest){
        return createStoneTypeUseCaseService.execute(stoneTypeDomainMapper.mapToDomain(stoneTypeRequest));
    }

    public StoneType update(Long id, StoneTypeUpdateRequest stoneTypeUpdateRequest){
        StoneType stoneType = getStoneTypeByIdUseCaseService.execute(id);
        return updateStoneTypeUseCaseService.execute(stoneTypeDomainMapper.mapToDomain(stoneType, stoneTypeUpdateRequest));
    }
}
