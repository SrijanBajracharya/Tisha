package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;

public interface WriteStoneTypeStorageService {

    StoneType create(StoneType stoneType);

    StoneType update(StoneType stoneType);
}
