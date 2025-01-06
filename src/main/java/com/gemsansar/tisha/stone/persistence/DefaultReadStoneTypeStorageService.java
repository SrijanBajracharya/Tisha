package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultReadStoneTypeStorageService implements ReadStoneTypeStorageService{

    private final StoneTypeEntityRepository stoneTypeEntityRepository;
    private final StoneTypeEntityMapper stoneTypeEntityMapper;

    @Override
    public List<StoneType> findAll(){
        List<StoneTypeEntity> stoneTypes = stoneTypeEntityRepository.findAll();
        return stoneTypeEntityMapper.mapToDomains(stoneTypes);
    }

    @Override
    public Optional<StoneType> findById(Long id) {
        return stoneTypeEntityRepository.findById(id).map(stoneTypeEntityMapper::mapToDomain);
    }
}
