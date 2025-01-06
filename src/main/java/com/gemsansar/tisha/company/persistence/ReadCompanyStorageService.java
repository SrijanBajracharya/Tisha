package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.domain.Company;

public interface ReadCompanyStorageService {

    Company findById(Long companyId);

}
