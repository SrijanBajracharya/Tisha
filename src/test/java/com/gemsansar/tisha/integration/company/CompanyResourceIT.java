package com.gemsansar.tisha.integration.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemsansar.tisha.company.domain.dto.request.CreateCompanyRequest;
import com.gemsansar.tisha.company.entities.CompanyEntity;
import com.gemsansar.tisha.company.persistence.CompanyEntityRepository;
import com.gemsansar.tisha.integration.IntegrationBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class CompanyResourceIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompanyEntityRepository companyEntityRepository;

    @BeforeEach
    void setup(){
        List<CompanyEntity> companyEntities = companyEntityRepository.findAll();
        assertThat(companyEntities.size()).isEqualTo(0);
    }

    @Test
    void create_shouldCreateCompany_whenRequestIsValid() throws Exception{
        CreateCompanyRequest request = CreateCompanyRequest.builder()
                .name("My Jyasa")
                .associationName("Nepal Jewellery association")
                .associationNumber("12-34-432")
                .panNumber("PAN-32221")
                .build();

        mockMvc.perform(post("/api/v1/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("My Jyasa"))
                .andExpect(jsonPath("$.associationName").value("Nepal Jewellery association"))
                .andExpect(jsonPath("$.associationNumber").value("12-34-432"))
                .andExpect(jsonPath("$.panNumber").value("PAN-32221"));

        List<CompanyEntity> companyEntities = companyEntityRepository.findAll();
        assertThat(companyEntities.size()).isEqualTo(1);
        CompanyEntity entity = companyEntities.getFirst();

        assertThat(entity.getCreatedDate()).isNotNull();
    }

    @Test
    void fetchById_shouldCreateCompany_whenRequestIsValid() throws Exception{
        CompanyEntity company = CompanyEntity.builder()
                .id(1L)
                .name("My Jyasa")
                .associationName("Nepal Jewellery association")
                .panNumber("PAN-32221")
                .associationNumber("12-34-432")
                .createdDate(Instant.now())
                .build();

        companyEntityRepository.save(company);

        mockMvc.perform(get("/api/v1/company/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("My Jyasa"))
                .andExpect(jsonPath("$.associationName").value("Nepal Jewellery association"))
                .andExpect(jsonPath("$.associationNumber").value("12-34-432"))
                .andExpect(jsonPath("$.panNumber").value("PAN-32221"));

        List<CompanyEntity> companyEntities = companyEntityRepository.findAll();
        assertThat(companyEntities.size()).isEqualTo(1);
        CompanyEntity entity = companyEntities.getFirst();

        assertThat(entity.getCreatedDate()).isNotNull();
    }
}
