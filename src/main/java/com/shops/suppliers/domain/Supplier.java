package com.shops.suppliers.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;

    private String name;
    private int checkingAccount;

    @OneToMany (mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();
}