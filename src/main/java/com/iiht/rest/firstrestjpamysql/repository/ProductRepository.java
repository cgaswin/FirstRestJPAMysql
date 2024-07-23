package com.iiht.rest.firstrestjpamysql.repository;

import com.iiht.rest.firstrestjpamysql.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
