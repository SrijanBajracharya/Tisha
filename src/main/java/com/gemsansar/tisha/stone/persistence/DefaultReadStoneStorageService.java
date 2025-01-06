package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.items.persistence.ItemEntityRepository;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultReadStoneStorageService implements ReadStoneStorageService{

    private final StoneEntityRepository stoneEntityRepository;
    private final StoneEntityMapper stoneEntityMapper;
    private final ItemEntityRepository itemEntityRepository;

    @Override
    public Optional<Stone> findById(Long id) {
        return stoneEntityRepository.findById(id).map(stoneEntityMapper::mapToDomain);
    }

    @Override
    public List<Stone> findByItemId(Long itemId) {
        ItemsEntity itemsEntity = itemEntityRepository.findById(itemId).orElseThrow(()-> new UseCaseException("Item not found with id: " + itemId));
        List<StoneEntity> entities = stoneEntityRepository.findByItem(itemsEntity);
        return stoneEntityMapper.mapToDomains(entities);
    }
}
