package com.gemsansar.tisha.items.resource;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.items.domain.dto.response.ItemResponse;
import org.springframework.stereotype.Component;

@Component
class ItemDomainMapper {

    public Item mapToDomain(Item item, ItemUpdateRequest request){
        return Item.builder()
                .id(item.getId())
                .name(request.getName())
                .cost(item.getCost())
                .comment(request.getComment())
                .purity(request.getPurity())
                .weight(request.getWeight())
                .status(request.getStatus())
                .build();
    }

    public ItemResponse mapToResponse(Item item){
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .weight(item.getWeight())
                .purity(item.getPurity())
                .status(item.getStatus())
                .comment(item.getComment())
                .rate(item.getRate())
                .build();
    }
}
