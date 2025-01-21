package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteItemStorageService implements WriteItemStorageService{

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public Item update(Item itemToSave) {
        ItemsEntity savedEntity = itemEntityRepository.save(itemEntityMapper.mapToEntity(itemToSave));
        return itemEntityMapper.mapToDomain(savedEntity);
    }
}
