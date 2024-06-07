package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.entity.orderdetail;
import com.example.ecommercespringboot.repository.orderdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class orderdetailService {
    @Autowired
    private orderdetailRepository orderdetailRepository;

    public List<orderdetail> getAllByorderdetail() {
        return orderdetailRepository.findAll();
    }


}
