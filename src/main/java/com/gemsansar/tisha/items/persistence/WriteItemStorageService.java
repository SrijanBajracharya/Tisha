package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.domain.Item;

public interface WriteItemStorageService {

    Item update(Item itemToSave);
}
