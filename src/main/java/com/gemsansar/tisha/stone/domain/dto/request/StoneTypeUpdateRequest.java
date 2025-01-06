package com.gemsansar.tisha.stone.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoneTypeUpdateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotNull
    private Double caret;
}
