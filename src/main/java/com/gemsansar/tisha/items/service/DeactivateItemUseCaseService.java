package com.gemsansar.tisha.items.service;

import com.gemsansar.tisha.items.domain.Item;

public interface DeactivateItemUseCaseService {

    Item handle(Long id);
}
