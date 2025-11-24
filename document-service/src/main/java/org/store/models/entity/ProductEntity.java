package org.store.models.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "SKU")
    private String sku; 
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "STOCKQUANTITY")
    private Integer stockQuantity;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "IMAGEURL")
    private String imageUrl;
    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt;

}
