package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.persistence.ReadStoneStorageService;
import com.gemsansar.tisha.stone.service.GetStoneByItemIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class DefaultGetStoneByItemIdUseCaseService implements GetStoneByItemIdUseCaseService {

    private final ReadStoneStorageService readStoneStorageService;

    @Override
    public List<Stone> execute(Long itemId) {
        return readStoneStorageService.findByItemId(itemId);
    }
}
