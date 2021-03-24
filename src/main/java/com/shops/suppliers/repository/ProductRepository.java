package com.shops.suppliers.repository;

import com.shops.suppliers.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
