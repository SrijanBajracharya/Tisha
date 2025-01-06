package com.gemsansar.tisha.items.resource;

import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.items.domain.dto.response.ItemResponse;
import com.gemsansar.tisha.items.service.GetItemService;
import com.gemsansar.tisha.items.service.UpdateItemUseCaseService;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class ItemGateway {

    private final ItemDomainMapper itemDomainMapper;
    private final GetItemService getItemService;
    private final UpdateItemUseCaseService updateItemUseCaseService;

    public ItemResponse update(Long id, ItemUpdateRequest request){
        User user = null;
        if(user == null){
            throw new UseCaseException("User not allowed to update the item.");
        }
        Optional<Item> itemOptional = getItemService.findByItemId(id);
        if(itemOptional.isEmpty()){
            throw new UseCaseException("Couldn't find item with id: " + id);
        }

        Item itemToSave = itemDomainMapper.mapToDomain(itemOptional.get(), request);
        Item savedItem = updateItemUseCaseService.handle(itemToSave);
        return itemDomainMapper.mapToResponse(savedItem);
    }
}
