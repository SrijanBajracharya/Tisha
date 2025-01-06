package com.gemsansar.tisha.company.resource;

import com.gemsansar.tisha.company.domain.dto.request.CreateCompanyRequest;
import com.gemsansar.tisha.company.domain.dto.response.CompanyResponse;
import com.gemsansar.tisha.company.domain.dto.response.CreateCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyResource {

    private final CompanyGateway companyGateway;

    @PostMapping
    public ResponseEntity<CreateCompanyResponse> create(@RequestBody CreateCompanyRequest createCompanyRequest){
        return ResponseEntity.ok(companyGateway.create(createCompanyRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getByCompanyId(@PathVariable Long id){
        return ResponseEntity.ok(companyGateway.getByCompanyId(id));
    }
}
