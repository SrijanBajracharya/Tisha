package com.gemsansar.tisha.stone.resource;

import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeRequest;
import com.gemsansar.tisha.stone.domain.dto.request.StoneTypeUpdateRequest;
import com.gemsansar.tisha.stone.service.CreateStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import com.gemsansar.tisha.stone.service.GetStoneTypeUseCaseService;
import com.gemsansar.tisha.stone.service.UpdateStoneTypeUseCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoneTypeGatewayTest {

    @Mock
    private GetStoneTypeUseCaseService getStoneTypeUseCaseService;
    @Mock
    private CreateStoneTypeUseCaseService createStoneTypeUseCaseService;
    @Mock
    private UpdateStoneTypeUseCaseService updateStoneTypeUseCaseService;
    @Mock
    private GetStoneTypeByIdUseCaseService getStoneTypeByIdUseCaseService;
    @InjectMocks
    private StoneTypeGateway underTest;

    @Captor
    private ArgumentCaptor<StoneType> stoneTypeArgumentCaptor;

    @BeforeEach
    void setup(){
        StoneTypeDomainMapper stoneTypeDomainMapper = new StoneTypeDomainMapper();
        underTest = new StoneTypeGateway(getStoneTypeUseCaseService, stoneTypeDomainMapper,
                createStoneTypeUseCaseService, updateStoneTypeUseCaseService, getStoneTypeByIdUseCaseService);
    }

    @Test
    void fetchAll_shouldFetchAllStoneType(){
        StoneType stoneType = StoneType.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();
        when(getStoneTypeUseCaseService.fetchAll()).thenReturn(List.of(stoneType));
        var response = underTest.fetchAll();
        assertThat(response.size()).isEqualTo(1);
        StoneType stoneTypeResult = response.getFirst();
        assertThat(stoneTypeResult.getId()).isEqualTo(1L);
        assertThat(stoneTypeResult.getName()).isEqualTo("Ruby");
        assertThat(stoneTypeResult.getType()).isEqualTo("sapphire");
        assertThat(stoneTypeResult.getCaret()).isEqualTo(22.0);
    }

    @Test
    void create_shouldCreateStoneType(){
        StoneTypeRequest request = StoneTypeRequest.builder()
                .name("Ruby")
                .caret(22.0)
                .type("sapphire")
                .build();
        StoneType stoneType = StoneType.builder()
                .id(1L)
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();
        when(createStoneTypeUseCaseService.execute(any())).thenReturn(stoneType);

        StoneType stoneTypeSaved = underTest.create(request);
        assertThat(stoneTypeSaved.getId()).isEqualTo(1L);
        assertThat(stoneTypeSaved.getName()).isEqualTo("Ruby");
        assertThat(stoneTypeSaved.getType()).isEqualTo("sapphire");
        assertThat(stoneTypeSaved.getCaret()).isEqualTo(22.0);

    }

    @Test
    void update_shouldUpdateStoneType(){
        StoneTypeUpdateRequest request = StoneTypeUpdateRequest.builder()
                .name("Ruby")
                .type("sapphire")
                .caret(22.0)
                .build();

        StoneType stoneType = StoneType.builder()
                .id(1L)
                .name("Marble")
                .type("crystal")
                .caret(22.0)
                .build();

        when(getStoneTypeByIdUseCaseService.execute(1L)).thenReturn(stoneType);

        underTest.update(1L, request);
        verify(updateStoneTypeUseCaseService).execute(stoneTypeArgumentCaptor.capture());
        StoneType stoneTypeToPersist = stoneTypeArgumentCaptor.getValue();
        assertThat(stoneTypeToPersist.getId()).isEqualTo(1L);
        assertThat(stoneTypeToPersist.getName()).isEqualTo("Ruby");
        assertThat(stoneTypeToPersist.getType()).isEqualTo("sapphire");
        assertThat(stoneTypeToPersist.getCaret()).isEqualTo(22.0);
    }
}