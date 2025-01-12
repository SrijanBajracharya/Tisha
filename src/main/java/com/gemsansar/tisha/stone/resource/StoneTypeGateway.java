package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.authentication.service.SessionService;
import com.gemsansar.tisha.platform.constants.PlatformConstant;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import com.gemsansar.tisha.stone.service.CreateStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.UpdateStoneTypeUseCaseService;
import com.gemsansar.tisha.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoneTypeGateway {

    private final SessionService sessionService;
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
        User currentUser = sessionService.getCurrentUser();
        if(!PlatformConstant.EDIT_ROLE.contains(currentUser.getRole())){
            throw new UseCaseException("User has no role to update stone type.");
        }
        StoneType stoneType = getStoneTypeByIdUseCaseService.execute(id);
        return updateStoneTypeUseCaseService.execute(stoneTypeDomainMapper.mapToDomain(stoneType, stoneTypeUpdateRequest));
    }
}
