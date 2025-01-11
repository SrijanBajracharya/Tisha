package com.gemsansar.tisha.order.entity;

import com.gemsansar.tisha.items.entities.ItemsEntity;
import com.gemsansar.tisha.order.domain.OrderStatus;
import com.gemsansar.tisha.platform.domain.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemsEntity> items;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

}
