package com.shops.suppliers.rest;

import com.shops.suppliers.domain.Product;
import com.shops.suppliers.domain.Supplier;
import com.shops.suppliers.repository.SupplierRepository;
import com.shops.suppliers.service.ProductServiceInterface;
import com.shops.suppliers.service.SupplierServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/")
public class ProductRestController {

    @Autowired
    private ProductServiceInterface productServiceInterface;

    @Autowired
    private SupplierServiceInterface supplierServiceInterface;

    @GetMapping(value = "{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId){
        if (productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = this.productServiceInterface.getByID(productId);

        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "{supplierId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct (@PathVariable("supplierId") Long supplierId, @RequestBody @Validated Product product){
        if (product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Supplier supplier = this.supplierServiceInterface.getById(supplierId);
        product.setSupplier(supplier);
        this.productServiceInterface.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody @Validated Product product){
        if (product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.productServiceInterface.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long id){
        Product product = this.productServiceInterface.getByID(id);

        if (product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.productServiceInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(){
        List <Product> allProducts = this.productServiceInterface.getAll();

        if (allProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsBySuppliers(@RequestParam("name") String supplierName){
        Supplier supplier = this.supplierServiceInterface.getByName(supplierName);
        return new ResponseEntity<>(supplier.getProducts(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping("name/")
    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long findProductByName(@RequestParam("name") String productName){
        Product product = this.productServiceInterface.getByName(productName);
        return product.getProductId();
    }
}
