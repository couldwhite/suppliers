package com.shops.suppliers.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer amount;
    private Long shelfLife;
    private String description;
}
