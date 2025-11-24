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
    public Long productId;
    @Column(name = "SKU")
    public String sku;
    @Column(name = "NAME")
    public String name;
    @Column(name = "DESCRIPTION")
    public String description;
    @Column(name = "PRICE")
    public BigDecimal price;
    @Column(name = "STATUS")
    public String status;
    @Column(name = "STOCKQUANTITY")
    public Integer stockQuantity;
    @Column(name = "CATEGORY")
    public String category;
    @Column(name = "IMAGEURL")
    public String imageUrl;
    @Column(name = "CREATEDAT")
    public LocalDateTime createdAt;
    @Column(name = "UPDATEDAT")
    public LocalDateTime updatedAt;

}
