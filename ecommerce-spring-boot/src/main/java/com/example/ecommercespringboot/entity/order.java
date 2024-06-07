package com.example.ecommercespringboot.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ordertable")
public class order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String orderid;

    @Column
    private String memberid;

    @Column
    private int price;

    @Column
    private String paystatus;

}
