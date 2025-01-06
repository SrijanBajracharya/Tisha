package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.Stone;

import java.util.List;
import java.util.Optional;

public interface ReadStoneStorageService {

    Optional<Stone> findById(Long id);

    List<Stone> findByItemId(Long itemId);

}
