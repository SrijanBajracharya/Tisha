package com.gemsansar.tisha.stone.service;

import com.gemsansar.tisha.stone.domain.StoneType;

import java.util.List;

public interface GetStoneTypeUseCaseService {

    List<StoneType> fetchAll();
}
