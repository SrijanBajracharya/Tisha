package com.gemsansar.tisha.cost.entities;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_COST")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemsEntity item;

    @Column(name = "jyala")
    private BigDecimal jyala;

    @Column(name = "jarti_quantity")
    private Double jartiQuantity;

}
