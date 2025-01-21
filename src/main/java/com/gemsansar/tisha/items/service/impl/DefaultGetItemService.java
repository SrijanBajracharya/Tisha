package com.gemsansar.tisha.items.service.impl;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.persistence.ReadItemStorageService;
import com.gemsansar.tisha.items.service.GetItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultGetItemService implements GetItemService {

    private final ReadItemStorageService readItemStorageService;

    @Transactional
    @Override
    public Optional<Item> findByItemId(Long id) {
        return readItemStorageService.findByItemId(id);
    }
}
