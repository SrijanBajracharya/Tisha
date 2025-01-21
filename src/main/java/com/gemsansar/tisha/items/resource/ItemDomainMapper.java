package com.gemsansar.tisha.items.resource;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.items.domain.dto.response.ItemUpdateResponse;
import com.gemsansar.tisha.platform.utils.PriceCalculation;
import com.gemsansar.tisha.user.domain.User;
import org.springframework.stereotype.Component;

@Component
class ItemDomainMapper {

    public Item mapToDomain(Item itemInDb, ItemUpdateRequest request, User user){

        itemInDb.setStatus(request.getStatus());
        itemInDb.setComment(request.getComment());
        itemInDb.setDueDate(request.getDueDate());
        itemInDb.setName(request.getName());
        itemInDb.setPurity(request.getPurity());
        itemInDb.setWeight(request.getWeight());
        itemInDb.setItemType(request.getItemType());
        itemInDb.setLastModifiedBy(user.getId());
        itemInDb.setActive(request.isActive());
        return itemInDb;
    }

    public ItemUpdateResponse mapToResponse(Item item){
        return ItemUpdateResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .weight(item.getWeight())
                .purity(item.getPurity())
                .status(item.getStatus())
                .comment(item.getComment())
                .rate(item.getRate())
                .itemType(item.getItemType())
                .total(PriceCalculation.calculateItemTotal(item))
                .active(item.isActive())
                .build();
    }
}
