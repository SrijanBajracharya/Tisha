package com.gemsansar.tisha.company.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "T_COMPANY")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pan_number", nullable = false)
    private String panNumber;

    @Column(name = "association_name", nullable = false)
    private String associationName;

    @Column(name = "association_number", nullable = false)
    private String associationNumber;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate = Instant.now();
}
