package org.store.models.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<ProductEntity> productEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERID")
    public UserEntity customer;
    @Column(name = "ADDRESORDER")
    public String addressOrder;
    @Column(name = "ORDERSTATUS")
    public String orderStatus;
    @Column(name = "DATEDELIVER")
    public LocalDateTime awaitDateDeliver;
}
