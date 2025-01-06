package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.persistence.WriteStoneTypeStorageService;
import com.gemsansar.tisha.stone.service.UpdateStoneTypeUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultUpdateStoneTypeUseCaseService implements UpdateStoneTypeUseCaseService {

    private final WriteStoneTypeStorageService writeStoneTypeStorageService;

    @Override
    public StoneType execute(StoneType stoneType) {
        return writeStoneTypeStorageService.update(stoneType);
    }
}
