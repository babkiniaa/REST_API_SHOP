package org.store.models.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.h2.engine.User;
import org.store.models.enums.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public UserStatus status;
    @Column(name = "REGISTERDATE")
    public LocalDateTime registeredAt;
    @Column(name = "BIRTHDAY")
    public LocalDate BirthDay;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERID")
    public List<OrderEntity> orderEntity;

    public static UserEntity findByEmail(String email) {
        return find("email = ?1", email).firstResult();
    }

    public static List<UserEntity> findActiveUsers() {
        return list("status", UserStatus.ACTIVE);
    }

    public static List<UserEntity> findByName(String name) {
        return list("LOWER(fullName) LIKE LOWER(?1)", "%" + name + "%");
    }

}
