package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.persistence.ReadStoneStorageService;
import com.gemsansar.tisha.stone.service.GetStoneByIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetStoneByIdUseCaseService implements GetStoneByIdUseCaseService {

    private final ReadStoneStorageService readStoneStorageService;

    @Override
    public Stone execute(Long id) {
        return readStoneStorageService.findById(id).orElseThrow(()-> new UseCaseException("Stone not found with id:" + id));
    }
}
