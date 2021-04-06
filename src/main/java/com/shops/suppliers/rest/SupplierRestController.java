package com.shops.suppliers.rest;

import com.shops.suppliers.domain.Product;
import com.shops.suppliers.domain.Supplier;
import com.shops.suppliers.service.SupplierServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suppliers/")
public class SupplierRestController {

    @Autowired
    private SupplierServiceInterface supplierServiceInterface;

    @GetMapping (value = "{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> getSupplier (@PathVariable("supplierId") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Supplier supplier = this.supplierServiceInterface.getById(id);

        if(supplier==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> saveSupplier(@RequestBody @Validated Supplier supplier){
        if (supplier==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.supplierServiceInterface.save(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("supplierId") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Supplier supplier = this.supplierServiceInterface.getById(id);
        if (supplier == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.supplierServiceInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Supplier> updateSupplier(@RequestBody @Validated Supplier supplier){
        if (supplier == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.supplierServiceInterface.save(supplier);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Supplier>> findAllSuppliers(){
        List<Supplier> suppliers = this.supplierServiceInterface.findAll();
        if(suppliers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("name/")
    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long findSupplierByName(@RequestParam("name") String supplierName){
        Supplier supplier = this.supplierServiceInterface.getByName(supplierName);
        return supplier.getSupplierId();
    }

}
