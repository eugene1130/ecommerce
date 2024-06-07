package com.example.ecommercespringboot.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String productid;

    @Column
    private String productname;

    @Column
    private int price;

    @Column
    private int quantity;


}
