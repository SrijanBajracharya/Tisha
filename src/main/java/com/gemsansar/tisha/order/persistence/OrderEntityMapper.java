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
        entity.setItems(mapToItems(order.getItems()));
        entity.setCustomerId(order.getCustomerId());
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
        return items.stream().map(this::mapToItem).toList();
    }

    private Item mapToItem(ItemsEntity item){
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
                .build();
    }

    private Cost mapToCostDomain(CostEntity cost){
        return Cost.builder()
                .id(cost.getId())
                .jyala(cost.getJyala())
                .build();
    }

    private List<ItemsEntity> mapToItems(List<Item> items){
        return items.stream().map(this::mapToItem).toList();
    }

    private ItemsEntity mapToItem(Item item){
        return ItemsEntity.builder()
                .name(item.getName())
                .comment(item.getComment())
                .dueDate(item.getDueDate())
                .purity(item.getPurity())
                .weight(item.getWeight())
                .cost(mapToCost(item.getCost()))
                .rate(item.getRate())
                .itemType(item.getItemType())
                .build();
    }

    private CostEntity mapToCost(Cost cost){
        return CostEntity.builder()
                .jyala(cost.getJyala())
                .build();
    }
}
