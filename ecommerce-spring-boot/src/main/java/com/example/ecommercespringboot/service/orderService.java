package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.entity.order;
import com.example.ecommercespringboot.repository.orderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {

    private static final Logger log = LoggerFactory.getLogger(orderService.class);

    @Autowired
    private  orderRepository orderRepository;

    public List<order> getAllOrders() {
        return orderRepository.findAll();
    }
}
