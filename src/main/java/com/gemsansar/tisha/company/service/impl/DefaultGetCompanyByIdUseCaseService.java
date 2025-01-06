package com.gemsansar.tisha.company.service.impl;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.persistence.ReadCompanyStorageService;
import com.gemsansar.tisha.company.service.GetCompanyByIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultGetCompanyByIdUseCaseService implements GetCompanyByIdUseCaseService {

    private final ReadCompanyStorageService readCompanyStorageService;

    @Override
    public Company handle(Long id) {
        return readCompanyStorageService.findById(id);
    }
}
