package com.gemsansar.tisha.integration.order;

import com.gemsansar.tisha.integration.IntegrationBaseTest;
import com.gemsansar.tisha.order.domain.dto.request.OrderRequest;
import com.gemsansar.tisha.order.entity.OrderEntity;
import com.gemsansar.tisha.order.persistence.OrderEntityRepository;
import com.gemsansar.tisha.platform.enums.AppRole;
import com.gemsansar.tisha.user.entity.UserEntity;
import com.gemsansar.tisha.user.persistence.UserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc(addFilters = false)
public class OrderResourceIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @BeforeEach
    void setup(){
        List<OrderEntity> entities = orderEntityRepository.findAll();
        assertTrue(entities.isEmpty());

        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstName("Srijan")
                .lastName("Bajracharya")
                .email("srijan.bajracharya@gmail.com")
                .role(AppRole.ADMIN)
                .companyId(1L)
                .password("1234")
                .build();

        userEntityRepository.save(user);
    }

    //@Test
    void create_shouldPersistOrder_whenRequestIsValid() throws Exception{
        OrderRequest request = OrderRequest.builder()
                .dueDate(Instant.now().plus(5, ChronoUnit.DAYS))
                .comment("Some random comment")
                .customerId(1L)
                .build();
    }
}
