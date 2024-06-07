package com.example.ecommercespringboot.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orderdetail")
public class orderdetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderitemsn;

    @Column
    private String orderid;

    @Column
    private String productid;

    @Column
    private int quantity;

    @Column
    private int standprice;

    @Column
    private int itemprice;

}
