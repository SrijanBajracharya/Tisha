package com.gemsansar.tisha.company.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class CompanyResponse {

    private Long id;
    private String name;
    private String panNumber;
    private String associationName;
    private String associationNumber;
    private Instant createdDate;
}
