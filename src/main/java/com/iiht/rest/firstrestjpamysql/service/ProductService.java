package com.iiht.rest.firstrestjpamysql.service;

import com.iiht.rest.firstrestjpamysql.entity.Product;
import com.iiht.rest.firstrestjpamysql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product save(Product product){
        return repository.save(product);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(long productId){
        Optional<Product> optional = repository.findById(productId);
        return optional.orElse(null);
    }

    public Product updateProduct(long productId,Product product){
        Optional<Product> optional = repository.findById(productId);
        if(optional.isPresent()){
            Product product1 = optional.get();
            product.setProductId(product1.getProductId());
            return repository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(long productId){
        Optional<Product> optional = repository.findById(productId);
        if(optional.isPresent()){
            Product product = optional.get();
            repository.delete(product);
            return true;
        }
        return false;
    }
}
