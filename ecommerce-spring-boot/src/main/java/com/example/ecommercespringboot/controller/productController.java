package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.entity.product;
import com.example.ecommercespringboot.service.productService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class productController {

    private static final Logger log = LoggerFactory.getLogger(productController.class);
    @Autowired
    private productService productService;

    @GetMapping
    public String listProducts(Model model) {//取得所有商品，然後傳給前端productList
        model.addAttribute("products", productService.getAllProducts());
        return "productList";
    }

    @GetMapping("/new")
    public String showNewProductForm(Model model) {//進到新增產品頁面
        model.addAttribute("product", new product());
        return "newProduct";
    }

    @PostMapping
    public String saveProduct(String productid,String productname,int price,int quantity,Model model ) {
        //處理產品新增，邏輯是將前端使用者打的產品資料都儲存

        log.info("productid received: {}", productid);
        log.info("productname received: {}", productname);
        log.info("price received: {}", price);
        log.info("quantity received: {}", quantity);

        product product =new product();
        product.setProductid(productid);
        product.setProductname(productname);
        product.setPrice(price);
        product.setQuantity(quantity);

        log.info("product received: {}", product);

        productService.saveProduct(product);
        model.addAttribute("products", productService.getAllProducts());
        return "productList";

    }

}

