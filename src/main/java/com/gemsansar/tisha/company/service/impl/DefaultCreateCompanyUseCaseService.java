package com.gemsansar.tisha.company.service.impl;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.persistence.WriteCompanyStorageService;
import com.gemsansar.tisha.company.service.CreateCompanyUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultCreateCompanyUseCaseService implements CreateCompanyUseCaseService {

    private final WriteCompanyStorageService writeCompanyStorageService;

    @Override
    public Company handle(Company company) {
        return writeCompanyStorageService.create(company);
    }
}
