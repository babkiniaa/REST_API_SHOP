package org.store.models.entities;

import java.time.LocalDateTime;

public class UserEntity {
    private Long userId;               // ID пользователя
    private String email;              // Уникальный email
    private String phoneNumber;        // Телефон
    private String fullName;           // Полное имя
    private UserStatus status;         // ACTIVE, BLOCKED, DELETED
    private LocalDateTime registeredAt;

}
