package com.iiht.rest.firstrestjpamysql.controller;

import com.iiht.rest.firstrestjpamysql.entity.Product;
import com.iiht.rest.firstrestjpamysql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping()
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody  Product productReq){
       Product product = service.save(productReq);
       return new  ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
}
