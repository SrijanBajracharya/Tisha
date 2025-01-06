package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoneEntityRepository extends JpaRepository<StoneEntity, Long> {

    List<StoneEntity> findByItem(ItemsEntity item);
}
