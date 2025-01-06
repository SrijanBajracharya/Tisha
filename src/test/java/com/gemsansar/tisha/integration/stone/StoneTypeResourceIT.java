package com.gemsansar.tisha.integration.stone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemsansar.tisha.integration.IntegrationBaseTest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import com.gemsansar.tisha.stone.persistence.StoneTypeEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class StoneTypeResourceIT extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoneTypeEntityRepository stoneTypeEntityRepository;

    @BeforeEach
    void setup(){

        List<StoneTypeEntity> entities = stoneTypeEntityRepository.findAll();
        assertThat(entities.size()).isEqualTo(0);

    }

    @Test
    void create_shouldCreateStoneType_whenRequestIsValid() throws Exception{
        StoneTypeRequest request = StoneTypeRequest.builder()
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();

        mockMvc.perform(
                post("/api/v1/stone-type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))

        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Ruby"))
        .andExpect(jsonPath("$.type").value("sapphire"))
        .andExpect(jsonPath("$.caret").value(22.0));


        List<StoneTypeEntity> stoneTypeEntities =stoneTypeEntityRepository.findAll();
        assertThat(stoneTypeEntities.size()).isEqualTo(1);
        StoneTypeEntity stoneTypeEntity = stoneTypeEntities.getFirst();
        assertThat(stoneTypeEntity.getType()).isEqualTo("sapphire");
        assertThat(stoneTypeEntity.getName()).isEqualTo("Ruby");
        assertThat(stoneTypeEntity.getCaret()).isEqualTo(22.0);
        assertNotNull(stoneTypeEntity.getId());
    }

    @Test
    void update_shouldUpdateStoneType_whenRequestIsValid() throws Exception{
        StoneTypeUpdateRequest request = StoneTypeUpdateRequest.builder()
                .name("Marble")
                .type("crystal")
                .caret(21.0)
                .build();
        StoneTypeEntity stoneTypeEntity = StoneTypeEntity.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();

        stoneTypeEntityRepository.save(stoneTypeEntity);
        List<StoneTypeEntity> stoneTypeEntities =stoneTypeEntityRepository.findAll();
        assertThat(stoneTypeEntities.size()).isEqualTo(1);
        mockMvc.perform(
                put("/api/v1/stone-type/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marble"))
                .andExpect(jsonPath("$.type").value("crystal"))
                .andExpect(jsonPath("$.caret").value(21.0));

    }
}
