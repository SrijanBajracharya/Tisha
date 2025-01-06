package com.gemsansar.tisha.company.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCompanyRequest {

    private String name;
    private String panNumber;
    private String associationName;
    private String associationNumber;
}
