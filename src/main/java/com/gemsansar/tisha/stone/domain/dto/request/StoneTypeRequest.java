package com.gemsansar.tisha.stone.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoneTypeRequest {

    private String name;
    private String type;
    private Double caret;
}
