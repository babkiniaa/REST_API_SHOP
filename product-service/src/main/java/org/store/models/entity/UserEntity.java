package org.store.models.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity(name = "USERS")
public class UserEntity extends PanacheEntityBase {
    @Id
    @Column(name = "USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;
    @Column(name = "EMAIL")
    public String email;
    @Column(name = "PHONENUMBER")
    public String phoneNumber;
    @Column(name = "FULLNAME")
    public String fullName;
    @Column(name = "STATUS")
    public String status;
    @Column(name = "REGISTERDATE")
    public LocalDateTime registeredAt;
    @Column(name = "BIRTHDAY")
    public LocalDate BirthDay;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERID")
    public List<OrderEntity> orderEntity;

}
