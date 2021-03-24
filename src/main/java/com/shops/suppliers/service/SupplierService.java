package com.shops.suppliers.service;

import com.shops.suppliers.domain.Supplier;
import com.shops.suppliers.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements SupplierServiceInterface{

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Supplier getById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}
