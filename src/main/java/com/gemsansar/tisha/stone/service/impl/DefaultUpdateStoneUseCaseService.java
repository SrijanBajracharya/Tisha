package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.persistence.WriteStoneStorageService;
import com.gemsansar.tisha.stone.service.UpdateStoneUseCaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultUpdateStoneUseCaseService implements UpdateStoneUseCaseService {

    private final WriteStoneStorageService writeStoneStorageService;

    @Transactional
    @Override
    public Stone execute(Stone stone) {
        return writeStoneStorageService.update(stone);
    }
}
