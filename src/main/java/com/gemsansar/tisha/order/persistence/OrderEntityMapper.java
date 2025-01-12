package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.entity.OrderEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import com.gemsansar.tisha.stone.persistence.StoneTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class OrderEntityMapper {

    private final StoneTypeEntityRepository stoneTypeEntityRepository;

    public OrderEntity mapToEntity(Order order){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus());
        entity.setComment(order.getComment());
        entity.setDueDate(order.getDueDate());
        entity.setLastModifiedBy(order.getLastModifiedBy());
        entity.getItems().addAll(mapToItemEntities(entity, order.getItems()));
        entity.setCustomerId(order.getCustomerId());
        entity.setCreatedBy(order.getCreateBy());
        return entity;
    }

    public Order mapToDomain(OrderEntity entity){
        return Order.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .comment(entity.getComment())
                .dueDate(entity.getDueDate())
                .lastModifiedBy(entity.getLastModifiedBy())
                .items(mapToItemsDomain(entity.getItems()))
                .customerId(entity.getCustomerId())
                .createBy(entity.getCreatedBy())
                .lastModifiedDate(entity.getLastModifiedDate())
                .build();
    }

    private List<Item> mapToItemsDomain(List<ItemsEntity> items){
        return items.stream().map(this::mapToItemEntity).toList();
    }

    private Item mapToItemEntity(ItemsEntity item){
        return Item.builder()
                .id(item.getId())
                .name(item.getName())
                .comment(item.getComment())
                .purity(item.getPurity())
                .weight(item.getWeight())
                .dueDate(item.getDueDate())
                .cost(mapToCostDomain(item.getCost()))
                .rate(item.getRate())
                .itemType(item.getItemType())
                .lastModifiedBy(item.getLastModifiedBy())
                .createdBy(item.getCreatedBy())
                .status(item.getStatus())
                .stones(mapToStones(item.getStones()))
                .build();
    }

    private List<Stone> mapToStones(List<StoneEntity> stones){
        if(CollectionUtils.isEmpty(stones)){
            return null;
        }
        return stones.stream().map(this::mapToStone).toList();
    }

    private Stone mapToStone(StoneEntity stone){
        return Stone.builder()
                .id(stone.getId())
                .price(stone.getPrice())
                .quantity(stone.getQuantity())
                .stoneTypeId(stone.getType().getId())
                .itemId(stone.getItem().getId())
                .build();
    }

    private Cost mapToCostDomain(CostEntity cost){
        return Cost.builder()
                .id(cost.getId())
                .jyala(cost.getJyala())
                .jartiQuantity(cost.getJartiQuantity())
                .itemId(cost.getItem().getId())
                .build();
    }

    private List<ItemsEntity> mapToItemEntities(OrderEntity orderEntity, List<Item> items){
        return items.stream().map(item -> mapToItemEntity(item, orderEntity)).toList();
    }

    private ItemsEntity mapToItemEntity(Item item, OrderEntity orderEntity){
        ItemsEntity itemsEntity = new ItemsEntity();
        itemsEntity.setId(item.getId());
        itemsEntity.setName(item.getName());
        itemsEntity.setComment(item.getComment());
        itemsEntity.setDueDate(item.getDueDate());
        itemsEntity.setPurity(item.getPurity());
        itemsEntity.setWeight(item.getWeight());
        itemsEntity.setRate(item.getRate());
        itemsEntity.setItemType(item.getItemType());
        itemsEntity.setOrder(orderEntity);
        itemsEntity.setCreatedBy(item.getCreatedBy());
        itemsEntity.setLastModifiedBy(item.getLastModifiedBy());
        itemsEntity.setStatus(item.getStatus());
        itemsEntity.setCost(mapToCostEntity(item.getCost(), itemsEntity));
        itemsEntity.getStones().addAll(mapToStoneEntities(item.getStones(), itemsEntity, item.getCreatedBy()));
        return itemsEntity;
    }

    private List<StoneEntity> mapToStoneEntities(List<Stone> stones, ItemsEntity itemsEntity, Long createdBy){
        if(CollectionUtils.isEmpty(stones)){
            return new ArrayList<>();
        }
        return stones.stream().map(stone -> mapToStoneEntity(stone, itemsEntity, createdBy)).toList();
    }

    private StoneEntity mapToStoneEntity(Stone stone, ItemsEntity itemsEntity, Long createdBy){
        StoneEntity entity = new StoneEntity();
        entity.setPrice(stone.getPrice());
        entity.setId(stone.getId());
        entity.setQuantity(stone.getQuantity());
        entity.setItem(itemsEntity);
        entity.setCreatedBy(createdBy);
        entity.setLastModifiedBy(createdBy);
        entity.setType(stoneTypeEntityRepository.findById(stone.getStoneTypeId()).orElseThrow(()-> new UseCaseException("Stone type not found with id:" + stone.getStoneTypeId())));
        return entity;
    }

    private CostEntity mapToCostEntity(Cost cost, ItemsEntity itemEntity){

        CostEntity costEntity = new CostEntity();
        costEntity.setId(cost.getId());
        costEntity.setItem(itemEntity);
        costEntity.setJyala(cost.getJyala());
        costEntity.setJartiQuantity(cost.getJartiQuantity());
        return costEntity;
    }
}
