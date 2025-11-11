package org.store.models.entities;

import jakarta.persistence.*;
import org.store.models.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity(name = "USERS")
public class UserEntity {
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
    public UserStatus status;
    @Column(name = "REGISTERDATE")
    public LocalDateTime registeredAt;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "")
    public List<OrderEntity> orderEntity;

}
