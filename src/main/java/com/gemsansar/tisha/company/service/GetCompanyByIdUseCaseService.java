package com.gemsansar.tisha.company.service;

import com.gemsansar.tisha.company.domain.Company;

public interface GetCompanyByIdUseCaseService {

    Company handle(Long id);
}
