package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteStoneStorageService implements WriteStoneStorageService{

    private final StoneEntityRepository stoneEntityRepository;
    private final StoneEntityMapper stoneEntityMapper;

    @Override
    public Stone update(Stone stone) {
        StoneEntity entity = stoneEntityMapper.mapToEntity(stone);
        return stoneEntityMapper.mapToDomain(stoneEntityRepository.save(entity));
    }
}
