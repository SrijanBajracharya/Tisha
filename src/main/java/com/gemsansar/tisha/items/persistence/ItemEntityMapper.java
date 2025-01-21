package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class ItemEntityMapper {

    private final ItemEntityRepository itemEntityRepository;

    public Item mapToDomain(ItemsEntity entity){
        return Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dueDate(entity.getDueDate())
                .purity(entity.getPurity())
                .weight(entity.getWeight())
                .comment(entity.getComment())
                .rate(entity.getRate())
                .status(entity.getStatus())
                .lastModifiedDate(entity.getLastModifiedDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .cost(mapToCost(entity.getCost()))
                .stones(mapToStones(entity.getStones()))
                .itemType(entity.getItemType())
                .active(entity.isActive())
                .build();
    }

    public ItemsEntity mapToEntity(Item item){
        ItemsEntity itemsEntity = itemEntityRepository.findById(item.getId()).orElseThrow();
        itemsEntity.setWeight(item.getWeight());
        itemsEntity.setPurity(item.getPurity());
        itemsEntity.setStatus(item.getStatus());
        itemsEntity.setLastModifiedBy(item.getLastModifiedBy());
        itemsEntity.setDueDate(item.getDueDate());
        itemsEntity.setName(item.getName());
        itemsEntity.setItemType(item.getItemType());
        itemsEntity.setStatus(item.getStatus());
        itemsEntity.setActive(item.isActive());
        return itemsEntity;
    }

    private List<Stone> mapToStones(List<StoneEntity> stoneEntities){
        if(CollectionUtils.isEmpty(stoneEntities)){
            return new ArrayList<>();
        }
        return stoneEntities.stream().map(this::mapToStone).toList();
    }

    private Stone mapToStone(StoneEntity stoneEntity){
        return Stone.builder()
                .stoneTypeId(stoneEntity.getType().getId())
                .id(stoneEntity.getId())
                .price(stoneEntity.getPrice())
                .quantity(stoneEntity.getQuantity())
                .itemId(stoneEntity.getItem().getId())
                .build();
    }

    private Cost mapToCost(CostEntity costEntity){
        return Cost.builder()
                .id(costEntity.getId())
                .itemId(costEntity.getItem().getId())
                .jartiQuantity(costEntity.getJartiQuantity())
                .jyala(costEntity.getJyala())
                .build();
    }

}
