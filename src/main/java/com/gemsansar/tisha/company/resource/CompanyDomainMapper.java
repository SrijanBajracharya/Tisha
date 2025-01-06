package com.gemsansar.tisha.company.resource;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.domain.dto.request.CreateCompanyRequest;
import com.gemsansar.tisha.company.domain.dto.response.CompanyResponse;
import com.gemsansar.tisha.company.domain.dto.response.CreateCompanyResponse;
import org.springframework.stereotype.Component;

@Component
class CompanyDomainMapper {

    public Company mapToDomain(CreateCompanyRequest request){
        return Company.builder()
                .name(request.getName())
                .associationNumber(request.getAssociationNumber())
                .associationName(request.getAssociationName())
                .panNumber(request.getPanNumber())
                .build();
    }

    public CreateCompanyResponse mapToResponse(Company company){
        return CreateCompanyResponse.builder()
                .id(company.getId())
                .associationNumber(company.getAssociationNumber())
                .name(company.getName())
                .associationName(company.getAssociationName())
                .panNumber(company.getPanNumber())
                .createdDate(company.getCreatedDate())
                .build();
    }

    public CompanyResponse mapToCompanyResponse(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .associationNumber(company.getAssociationNumber())
                .name(company.getName())
                .associationName(company.getAssociationName())
                .panNumber(company.getPanNumber())
                .createdDate(company.getCreatedDate())
                .build();
    }

}
