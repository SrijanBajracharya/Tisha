package com.gemsansar.tisha.cost.resource;

import com.gemsansar.tisha.cost.domain.request.CostUpdateRequest;
import com.gemsansar.tisha.cost.domain.response.CostUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cost")
@RequiredArgsConstructor
public class CostResource {

    private final CostGateway costGateway;

    @PutMapping("/{id}")
    public ResponseEntity<CostUpdateResponse> update(@PathVariable Long id, @RequestBody CostUpdateRequest costUpdateRequest){
        return ResponseEntity.ok(costGateway.update(id, costUpdateRequest));
    }
}
