package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.entity.orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderdetailRepository extends JpaRepository<orderdetail, Long> {

    List<orderdetail> findAll();
}
