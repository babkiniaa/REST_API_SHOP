package org.store.models.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table
@Entity(name = "ORDERS")
public class OrderEntity extends PanacheEntityBase {
    @Id
    @Column(name = "ORDERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;
    @Column(name = "ORDERNUMBER")
    public String orderNumber;
    @Column(name = "DATECREATE")
    public LocalDateTime dateCreate;
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "PRODUCTID")
    public OrderEntity orderEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID")
    public UserEntity customer;

}
