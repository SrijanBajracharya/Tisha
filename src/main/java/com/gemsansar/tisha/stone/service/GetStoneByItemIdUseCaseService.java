package com.gemsansar.tisha.stone.service;

import com.gemsansar.tisha.stone.domain.Stone;

import java.util.List;

public interface GetStoneByItemIdUseCaseService {

    List<Stone> execute(Long itemId);
}
