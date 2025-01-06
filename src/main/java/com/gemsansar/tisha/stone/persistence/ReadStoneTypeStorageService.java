package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;

import java.util.List;
import java.util.Optional;

public interface ReadStoneTypeStorageService {

    List<StoneType> findAll();

    Optional<StoneType> findById(Long id);
}
