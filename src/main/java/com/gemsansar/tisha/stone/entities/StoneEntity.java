package com.gemsansar.tisha.stone.entities;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.platform.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_STONE")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class StoneEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private StoneTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemsEntity item;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
