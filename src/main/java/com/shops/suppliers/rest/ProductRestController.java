package com.shops.suppliers.rest;

import com.shops.suppliers.domain.Product;
import com.shops.suppliers.service.ProductServiceInterface;
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

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        if (productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = this.productServiceInterface.getByID(productId);

        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct (@RequestBody @Validated Product product){
        if (product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
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
}
