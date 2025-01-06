package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stone-type")
@RequiredArgsConstructor
public class StoneTypeResource {

    private final StoneTypeGateway stoneTypeGateway;

    @GetMapping
    public ResponseEntity<List<StoneType>> fetchAll(){
        return ResponseEntity.ok(stoneTypeGateway.fetchAll());
    }

    @PostMapping
    public ResponseEntity<StoneType> create(@RequestBody StoneTypeRequest stoneTypeRequest){
        return ResponseEntity.ok(stoneTypeGateway.create(stoneTypeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoneType> update(@PathVariable Long id, @RequestBody StoneTypeUpdateRequest stoneTypeUpdateRequest){
        return ResponseEntity.ok(stoneTypeGateway.update(id, stoneTypeUpdateRequest));
    }
}
