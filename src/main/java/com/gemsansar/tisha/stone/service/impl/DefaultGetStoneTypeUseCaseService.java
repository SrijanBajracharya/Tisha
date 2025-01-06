package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.persistence.ReadStoneTypeStorageService;
import com.gemsansar.tisha.stone.service.GetStoneTypeUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class DefaultGetStoneTypeUseCaseService implements GetStoneTypeUseCaseService {

    private final ReadStoneTypeStorageService readStoneTypeStorageService;

    @Override
    public List<StoneType> fetchAll() {
        return readStoneTypeStorageService.findAll();
    }
}
