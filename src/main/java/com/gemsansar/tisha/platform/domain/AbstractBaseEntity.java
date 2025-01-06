package com.gemsansar.tisha.platform.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EnableJpaAuditing
public class AbstractBaseEntity {

	@JsonIgnore
	@CreatedBy
	@Column(name = "created_by", nullable = false, updatable = false)
	private Long createdBy;

	@JsonIgnore
	@CreatedDate
	@Column(name = "created_date", nullable = false, updatable = false)
	private Instant createdDate = Instant.now();

	@JsonIgnore
	@LastModifiedBy
	@Column(name = "last_modified_by")
	private Long lastModifiedBy;

	@JsonIgnore
	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Instant lastModifiedDate = Instant.now();

}
