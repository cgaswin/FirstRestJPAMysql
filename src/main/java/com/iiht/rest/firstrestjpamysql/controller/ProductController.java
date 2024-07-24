package com.iiht.rest.firstrestjpamysql.controller;

import com.iiht.rest.firstrestjpamysql.entity.Product;
import com.iiht.rest.firstrestjpamysql.exception.InvalidValueException;
import com.iiht.rest.firstrestjpamysql.service.ProductService;
import org.hibernate.boot.spi.NaturalIdUniqueKeyBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService service;


    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> products = service.getAllProducts();
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable long productId){
        Product product = service.getProductById(productId);
        if(product!=null){
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Product is not available",HttpStatus.NOT_FOUND);
    }

    //checking if exception handling is working
    @GetMapping("/test")
    public String message(String str) throws InvalidValueException,Exception {
        str="abc1$#@";
        if(str==null)
            throw new NullPointerException("value not provided");
        boolean isNumeric = str.chars().anyMatch(Character::isDigit);
        if(str.length()<8)
            throw new Exception("Length is less than 8 Characters");
        if(isNumeric)
            throw new InvalidValueException("Expected String found int");
        else
            return "hello...";
    }

    @PostMapping()
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody  Product productReq){
       Product product = service.save(productReq);
       return new  ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable long productId,@RequestBody Product product){
        Product newProduct = service.updateProduct(productId,product);
        if(newProduct!=null){
            return new ResponseEntity<Product>(newProduct,HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        boolean response = service.deleteProduct(productId);
        if(response){
            return new ResponseEntity<>("Product deleted",HttpStatus.OK);
        }

        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }


}
