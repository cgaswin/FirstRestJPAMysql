package com.iiht.rest.firstrestjpamysql.service;

import com.iiht.rest.firstrestjpamysql.entity.Product;
import com.iiht.rest.firstrestjpamysql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product save(Product product){
        return repository.save(product);
    }
}
