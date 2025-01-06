package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.domain.Item;

import java.util.Optional;

public interface ReadItemStorageService {

    Optional<Item> findByItemId(Long id);
}
