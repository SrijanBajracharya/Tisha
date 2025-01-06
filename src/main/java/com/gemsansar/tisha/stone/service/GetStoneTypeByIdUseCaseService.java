package com.gemsansar.tisha.stone.service;

import com.gemsansar.tisha.stone.domain.StoneType;

public interface GetStoneTypeByIdUseCaseService {

    StoneType execute(Long id);
}
