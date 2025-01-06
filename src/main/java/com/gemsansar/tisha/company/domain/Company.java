package com.gemsansar.tisha.company.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class Company {

    private Long id;
    private String name;
    private String associationName;
    private String associationNumber;
    private String panNumber;
    private Instant createdDate;
}
