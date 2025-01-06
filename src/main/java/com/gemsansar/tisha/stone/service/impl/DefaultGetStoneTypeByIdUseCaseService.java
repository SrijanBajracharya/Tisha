package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.persistence.ReadStoneTypeStorageService;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetStoneTypeByIdUseCaseService implements GetStoneTypeByIdUseCaseService {

    private final ReadStoneTypeStorageService readStoneTypeStorageService;

    @Override
    public StoneType execute(Long id) {
        return readStoneTypeStorageService.findById(id).orElseThrow(()-> new UseCaseException("Stone type not found with id:" + id));
    }
}
