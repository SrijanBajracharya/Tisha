package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.entities.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
class CompanyEntityMapper {

    public Company mapToDomain(CompanyEntity entity){
        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .associationName(entity.getAssociationName())
                .associationNumber(entity.getAssociationNumber())
                .panNumber(entity.getPanNumber())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    public CompanyEntity mapToEntity(Company company){
        return CompanyEntity.builder()
                .id(company.getId())
                .name(company.getName())
                .associationName(company.getAssociationName())
                .associationNumber(company.getAssociationNumber())
                .panNumber(company.getPanNumber())
                .build();
    }
}
