package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<order, Long> {
}
