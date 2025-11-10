package org.store.models.entities;

public class OrderEntity {
    private String orderId;
    private Long userId;               // Внешний ключ на пользователя
    private UserEntity customer;       // или только reference

}
