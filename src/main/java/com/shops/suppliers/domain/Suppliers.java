package com.shops.suppliers.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long checkingAccount;
}