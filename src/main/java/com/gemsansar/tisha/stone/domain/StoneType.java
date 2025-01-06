package com.gemsansar.tisha.stone.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoneType {

    private Long id;
    private String name;
    private Double caret;
    private String type;

}
