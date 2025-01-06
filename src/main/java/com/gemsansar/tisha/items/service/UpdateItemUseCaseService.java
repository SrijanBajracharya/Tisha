package com.gemsansar.tisha.items.service;

import com.gemsansar.tisha.items.domain.Item;

public interface UpdateItemUseCaseService {

    Item handle(Item itemToSave);
}
