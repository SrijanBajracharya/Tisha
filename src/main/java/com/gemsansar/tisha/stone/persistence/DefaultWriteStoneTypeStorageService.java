package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteStoneTypeStorageService implements WriteStoneTypeStorageService{

    private final StoneTypeEntityRepository stoneTypeEntityRepository;
    private final StoneTypeEntityMapper stoneTypeEntityMapper;

    @Override
    public StoneType create(StoneType stoneType) {
        StoneTypeEntity entity = stoneTypeEntityRepository.save(stoneTypeEntityMapper.mapToEntity(stoneType));
        return stoneTypeEntityMapper.mapToDomain(entity);
    }

    @Override
    public StoneType update(StoneType stoneType) {
        StoneTypeEntity entity = stoneTypeEntityRepository.save(stoneTypeEntityMapper.mapToEntity(stoneType));
        return stoneTypeEntityMapper.mapToDomain(entity);
    }
}
