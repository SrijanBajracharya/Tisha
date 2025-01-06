package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemsEntity, Long> {
}
