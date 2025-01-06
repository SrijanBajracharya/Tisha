package com.gemsansar.tisha.items.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
                .lastModifiedDate(entity.getLastModifiedDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }

    public ItemsEntity mapToEntity(Item item, Long itemId){
        return ItemsEntity.builder()
                .id(item.getId())
                .name(item.getName())
                .comment(item.getComment())
                .dueDate(item.getDueDate())
                .purity(item.getPurity())
                .weight(item.getWeight())
                .cost(mapToCost(item.getCost(), itemId))
                .status(item.getStatus())
                .build();
    }

    private CostEntity mapToCost(Cost cost, Long itemId){
        ItemsEntity item = itemEntityRepository.findById(itemId).orElseThrow(()-> new UseCaseException("Couldn't find item with id: " + itemId));
        return CostEntity.builder()
                .id(cost.getId())
                .item(item)
                .jyala(cost.getJyala())
                .jartiQuantity(cost.getJartiQuantity())
                .build();
    }
}
