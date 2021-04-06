package com.shops.suppliers.service;

import com.shops.suppliers.domain.Supplier;

import java.util.List;

public interface SupplierServiceInterface {

    Supplier getById (Long id);

    void save (Supplier supplier);

    void delete (Long id);

    List<Supplier> findAll();

    Supplier getByName (String name);
}
