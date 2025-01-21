package com.gemsansar.tisha.items.service.impl;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.persistence.WriteItemStorageService;
import com.gemsansar.tisha.items.service.DeactivateItemUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultDeactivateItemUseCaseService implements DeactivateItemUseCaseService {

    private final WriteItemStorageService writeItemStorageService;

    @Override
    public Item handle(Long id) {
        return writeItemStorageService.deactivate(id);
    }
}
