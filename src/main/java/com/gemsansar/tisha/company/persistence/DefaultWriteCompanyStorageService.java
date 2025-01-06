package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.entities.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteCompanyStorageService implements WriteCompanyStorageService{

    private final CompanyEntityRepository companyEntityRepository;
    private final CompanyEntityMapper companyEntityMapper;

    @Override
    public Company create(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);
        return companyEntityMapper.mapToDomain(companyEntityRepository.save(companyEntity));
    }
}
