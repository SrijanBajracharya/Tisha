package com.gemsansar.tisha.cost.service;

import com.gemsansar.tisha.cost.domain.Cost;

public interface GetCostByIdUseCaseService {

    Cost execute(Long id);
}
