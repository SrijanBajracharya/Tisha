package com.gemsansar.tisha.stone.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_STONE_TYPE")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class StoneTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "caret", nullable = false)
    private Double caret;
}
