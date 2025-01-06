package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.dto.request.StoneUpdateRequest;
import com.gemsansar.tisha.stone.domain.dto.response.StoneResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stone")
@RequiredArgsConstructor
public class StoneResource {

    private final StoneGateway stoneGateway;

    @PutMapping("/{id}")
    public ResponseEntity<StoneResponse> update(@PathVariable Long id, @RequestBody StoneUpdateRequest stoneUpdateRequest){
        return ResponseEntity.ok(stoneGateway.update(id, stoneUpdateRequest));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<List<StoneResponse>> getByItemId(@PathVariable Long itemId){
        return ResponseEntity.ok(stoneGateway.getByItemId(itemId));
    }
}
