package com.gemsansar.tisha.items.service.impl;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.persistence.WriteItemStorageService;
import com.gemsansar.tisha.items.service.UpdateItemUseCaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultUpdateItemUseCaseService implements UpdateItemUseCaseService {

    private final WriteItemStorageService writeItemStorageService;

    @Transactional
    @Override
    public Item handle(Item itemToSave) {
        return writeItemStorageService.update(itemToSave);
    }
}
