package com.shops.suppliers.service;

import com.shops.suppliers.domain.Product;
import com.shops.suppliers.domain.Supplier;

import java.util.List;

public interface ProductServiceInterface {

    Product getByID(Long id);

    void save (Product product);

    void delete (Long id);

    List<Product> getAll();

    Product getByName (String name);
}
