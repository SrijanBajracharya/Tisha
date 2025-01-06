package com.gemsansar.tisha.integration.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemsansar.tisha.company.entities.CompanyEntity;
import com.gemsansar.tisha.company.persistence.CompanyEntityRepository;
import com.gemsansar.tisha.integration.IntegrationBaseTest;
import com.gemsansar.tisha.platform.enums.AppRole;
import com.gemsansar.tisha.user.domain.dto.request.CreateUserRequest;
import com.gemsansar.tisha.user.domain.dto.request.LoginUserRequest;
import com.gemsansar.tisha.user.entity.UserEntity;
import com.gemsansar.tisha.user.persistence.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class UserResourceIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private CompanyEntityRepository companyEntityRepository;

    @BeforeEach
    void setup(){

        List<UserEntity> userEntities = userEntityRepository.findAll();
        assertThat(userEntities.size()).isEqualTo(0);

        CompanyEntity company = CompanyEntity.builder()
                .id(1L)
                .name("My Jyasa")
                .associationName("Nepal Jewellery association")
                .panNumber("PAN-32221")
                .associationNumber("12-34-432")
                .createdDate(Instant.now())
                .build();

        companyEntityRepository.save(company);

    }

    @Test
    void create_shouldCreateUser_whenRequestIsValid() throws Exception{
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("Srijan")
                .lastName("Bajracharya")
                .email("srijan.bajracharya@gmail.com")
                .role(AppRole.ADMIN)
                .companyId(1L)
                .password("1234")
                .confirmPassword("1234")
                .build();

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        List<UserEntity> userEntities = userEntityRepository.findAll();
        assertThat(userEntities.size()).isEqualTo(1);
    }

    @Test
    void login_shouldValidate_whenRequestIsValid() throws Exception{
        UserEntity user = UserEntity.builder()
                .firstName("Srijan")
                .lastName("Bajracharya")
                .email("srijan.bajracharya@gmail.com")
                .role(AppRole.ADMIN)
                .companyId(1L)
                .password("1234")
                .build();

        userEntityRepository.save(user);


        LoginUserRequest request = LoginUserRequest.builder()
                .email("srijan.bajracharya@gmail.com")
                .password("1234")
                .build();

        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
