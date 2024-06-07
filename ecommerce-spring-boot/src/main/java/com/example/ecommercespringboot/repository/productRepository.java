package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface productRepository extends JpaRepository<product, String> {

     product findByproductid(String productid);

}
