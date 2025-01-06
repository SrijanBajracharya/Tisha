package com.gemsansar.tisha.items.entities;

import com.gemsansar.tisha.cost.entities.CostEntity;
import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.order.domain.ItemType;
import com.gemsansar.tisha.order.entity.OrderEntity;
import com.gemsansar.tisha.platform.domain.AbstractBaseEntity;
import com.gemsansar.tisha.stone.entities.StoneEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "T_ITEMS")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemsEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gold_rate", nullable = false)
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;

    @OneToMany(mappedBy = "item")
    private List<StoneEntity> stones;

    @OneToOne(mappedBy = "item")
    private CostEntity cost;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "purity")
    private Double purity;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
}
