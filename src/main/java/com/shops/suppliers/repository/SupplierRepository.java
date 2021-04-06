package com.shops.suppliers.repository;

import com.shops.suppliers.domain.Product;
import com.shops.suppliers.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findByName(String name);

}
