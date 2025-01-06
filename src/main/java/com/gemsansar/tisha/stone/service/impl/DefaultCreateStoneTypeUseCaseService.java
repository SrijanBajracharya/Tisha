package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.persistence.WriteStoneTypeStorageService;
import com.gemsansar.tisha.stone.service.CreateStoneTypeUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultCreateStoneTypeUseCaseService implements CreateStoneTypeUseCaseService {

    private final WriteStoneTypeStorageService writeStoneTypeStorageService;

    @Override
    public StoneType execute(StoneType stoneType) {
        return writeStoneTypeStorageService.create(stoneType);
    }
}
