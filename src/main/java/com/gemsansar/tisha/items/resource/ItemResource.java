package com.gemsansar.tisha.items.resource;

import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.items.domain.dto.response.ItemUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemResource {

    private final ItemGateway itemGateway;

    @PutMapping("/{id}")
    public ResponseEntity<ItemUpdateResponse> update(@PathVariable Long id, @RequestBody ItemUpdateRequest request){
        return ResponseEntity.ok(itemGateway.update(id, request));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ItemUpdateResponse> activate(@PathVariable Long id){
        return ResponseEntity.ok(itemGateway.activate(id));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ItemUpdateResponse> deactivate(@PathVariable Long id){
        return ResponseEntity.ok(itemGateway.deactivate(id));
    }
}
