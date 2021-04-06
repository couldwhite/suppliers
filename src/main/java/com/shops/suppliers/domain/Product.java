package com.shops.suppliers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String name;
    private int amount;
    private int shelfLife;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;
}
