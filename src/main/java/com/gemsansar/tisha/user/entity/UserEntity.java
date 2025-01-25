package com.gemsansar.tisha.user.entity;

import com.gemsansar.tisha.platform.enums.AppRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "T_USER")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "password", length = 600, nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppRole role;

    @Column(name = "active")
    private boolean active;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "deactivated_timestamp")
    private Instant deactivatedTimestamp;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate = Instant.now();
}
