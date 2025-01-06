package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.domain.Company;

public interface WriteCompanyStorageService {

    Company create(Company company);
}
