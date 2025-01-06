package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class DefaultReadItemStorageService implements ReadItemStorageService {

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public Optional<Item> findByItemId(Long id) {
        return itemEntityRepository.findById(id).map(itemEntityMapper::mapToDomain);
    }
}
