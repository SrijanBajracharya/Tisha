package com.gemsansar.tisha.order.persistence;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class OrderEntityMapper {

    public OrderEntity mapToEntity(Order order){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus());
        entity.setComment(order.getComment());
        entity.setDueDate(order.getDueDate());
        entity.setLastModifiedBy(order.getLastModifiedBy());
        entity.setItems(mapToItemEntities(entity, order.getItems()));
        entity.setCustomerId(order.getCustomerId());
        entity.setCreatedBy(order.getCreateBy());
        return entity;
    }

    public List<Order> mapToDomains(List<OrderEntity> entities){
        return entities.stream().map(this::mapToDomain).toList();
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
        return itemsEntity;
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
