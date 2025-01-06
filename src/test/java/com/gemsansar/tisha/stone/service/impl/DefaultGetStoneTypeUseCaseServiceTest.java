package com.gemsansar.tisha.stone.service.impl;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.persistence.ReadStoneTypeStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultGetStoneTypeUseCaseServiceTest {

    @Mock
    private ReadStoneTypeStorageService readStoneTypeStorageService;

    @InjectMocks
    private DefaultGetStoneTypeUseCaseService underTest;

    @Test
    void fetchAll_shouldFetchAll(){
        StoneType stoneType = StoneType.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();

        StoneType stoneType1 = StoneType.builder()
                .id(2L)
                .name("Marble")
                .type("crystal")
                .caret(21.0)
                .build();

        when(readStoneTypeStorageService.findAll()).thenReturn(List.of(stoneType, stoneType1));

        List<StoneType> stoneTypes = underTest.fetchAll();
        assertThat(stoneTypes.size()).isEqualTo(2);
    }

}