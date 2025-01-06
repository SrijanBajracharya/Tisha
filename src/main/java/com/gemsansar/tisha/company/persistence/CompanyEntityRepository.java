package com.gemsansar.tisha.company.persistence;

import com.gemsansar.tisha.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyEntityRepository extends JpaRepository<CompanyEntity, Long> {
}
