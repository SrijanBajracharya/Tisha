package com.gemsansar.tisha.company.resource;

import com.gemsansar.tisha.company.domain.Company;
import com.gemsansar.tisha.company.domain.dto.request.CreateCompanyRequest;
import com.gemsansar.tisha.company.domain.dto.response.CompanyResponse;
import com.gemsansar.tisha.company.domain.dto.response.CreateCompanyResponse;
import com.gemsansar.tisha.company.service.CreateCompanyUseCaseService;
import com.gemsansar.tisha.company.service.GetCompanyByIdUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CompanyGateway {

    private final CreateCompanyUseCaseService createCompanyUseCaseService;
    private final CompanyDomainMapper companyDomainMapper;
    private final GetCompanyByIdUseCaseService getCompanyByIdUseCaseService;

    public CreateCompanyResponse create(CreateCompanyRequest companyRequest){
        Company company = companyDomainMapper.mapToDomain(companyRequest);
        return companyDomainMapper.mapToResponse(createCompanyUseCaseService.handle(company));
    }

    public CompanyResponse getByCompanyId(Long id){
        Company company = getCompanyByIdUseCaseService.handle(id);
        return companyDomainMapper.mapToCompanyResponse(company);
    }

}
