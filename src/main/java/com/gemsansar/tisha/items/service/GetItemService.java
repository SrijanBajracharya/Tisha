package com.gemsansar.tisha.items.service;

import com.gemsansar.tisha.items.domain.Item;

import java.util.Optional;

public interface GetItemService {

    Optional<Item> findByItemId(Long id);

}
