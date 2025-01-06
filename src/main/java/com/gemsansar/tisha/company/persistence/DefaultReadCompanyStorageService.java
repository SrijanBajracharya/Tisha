package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.entities.CompanyEntity;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultReadCompanyStorageService implements ReadCompanyStorageService{

    private final CompanyEntityRepository companyEntityRepository;
    private final CompanyEntityMapper companyEntityMapper;

    @Override
    public Company findById(Long companyId) {
        CompanyEntity companyEntity = companyEntityRepository.findById(companyId).orElseThrow(()-> new UseCaseException("Company not found with id:" + companyId));
        return companyEntityMapper.mapToDomain(companyEntity);
    }
}
