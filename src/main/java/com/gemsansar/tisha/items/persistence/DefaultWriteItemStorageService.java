package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
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

    @Override
    public Item activate(Long id) {
        ItemsEntity itemsEntity = itemEntityRepository.findById(id).orElseThrow(()-> new UseCaseException("Item not found with id: " + id));
        itemsEntity.setActive(true);
        return itemEntityMapper.mapToDomain(itemEntityRepository.save(itemsEntity));
    }

    @Override
    public Item deactivate(Long id) {
        ItemsEntity itemsEntity = itemEntityRepository.findById(id).orElseThrow(()-> new UseCaseException("Item not found with id: " + id));
        itemsEntity.setActive(false);
        return itemEntityMapper.mapToDomain(itemEntityRepository.save(itemsEntity));
    }
}
