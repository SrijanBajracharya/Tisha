package com.gemsansar.tisha.stone.persistence;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.entities.StoneTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultReadStoneTypeStorageServiceTest {

    @Mock
    private StoneTypeEntityRepository stoneTypeEntityRepository;

    @InjectMocks
    private DefaultReadStoneTypeStorageService underTest;

    @BeforeEach
    void setup(){
        StoneTypeEntityMapper stoneTypeEntityMapper = new StoneTypeEntityMapper();
        underTest = new DefaultReadStoneTypeStorageService(stoneTypeEntityRepository, stoneTypeEntityMapper);
    }

    @Test
    void findAll_shouldFetchAllStoneType(){
        StoneTypeEntity stoneTypeEntity = StoneTypeEntity.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();

        StoneTypeEntity stoneTypeEntity1 = StoneTypeEntity.builder()
                .id(2L)
                .name("Marble")
                .type("crystal")
                .caret(21.0)
                .build();

        when(stoneTypeEntityRepository.findAll()).thenReturn(List.of(stoneTypeEntity, stoneTypeEntity1));
        List<StoneType> stoneTypes = underTest.findAll();
        assertThat(stoneTypes.size()).isEqualTo(2);
        assertThat(stoneTypes.getFirst().getId()).isEqualTo(1L);
        assertThat(stoneTypes.getLast().getId()).isEqualTo(2L);
        assertThat(stoneTypes.getFirst().getName()).isEqualTo("Ruby");
        assertThat(stoneTypes.getLast().getName()).isEqualTo("Marble");
        assertThat(stoneTypes.getFirst().getType()).isEqualTo("sapphire");
        assertThat(stoneTypes.getLast().getType()).isEqualTo("crystal");
    }

    @Test
    void findById_shouldReturnEmpty_whenEntityNotFound(){
        var response = underTest.findById(1L);
        assertTrue(response.isEmpty());
    }

    @Test
    void findById_shouldReturn_whenEntityFound(){
        StoneTypeEntity stoneTypeEntity = StoneTypeEntity.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();
        when(stoneTypeEntityRepository.findById(1L)).thenReturn(Optional.of(stoneTypeEntity));
        var response = underTest.findById(1L);
        assertTrue(response.isPresent());
        StoneType stoneType = response.get();
        assertThat(stoneType.getId()).isEqualTo(1L);
        assertThat(stoneType.getName()).isEqualTo("Ruby");
        assertThat(stoneType.getType()).isEqualTo("sapphire");
    }

}