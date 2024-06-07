package com.example.ecommercespringboot.controller;

import com.example.ecommercespringboot.entity.product;
import com.example.ecommercespringboot.service.productService;
import com.example.ecommercespringboot.service.orderdetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class orderController {

    private static final Logger log = LoggerFactory.getLogger(orderController .class);

    @Autowired
    private productService productService;
    @Autowired
    private orderdetailService orderdetailService;


    @GetMapping
    public String listProducts(Model model) {//將產品不為0的資訊列出，並前往產品不為0的頁面，也是下訂單頁面
        List<product> availableProducts = productService.getAllProductsNonNull();
        model.addAttribute("products", availableProducts);
        return "productListNonNull";
    }

    @PostMapping
    public String createOrder(@RequestParam Map<String, String> quantities, @RequestParam List<String> productIds, Model model) {

        //處理新增訂單的邏輯
        List<Integer> parsedQuantities = new ArrayList<>();
        List<String> filteredProductIds = new ArrayList<>();

        //抓取前端取得的productIds，然後判斷為不為0，為了避免所有產品都要寫數字才能新增的情況，才會做這判斷
        for (int i = 0; i < productIds.size(); i++) {
            String key = "quantities[" + i + "]";
            if (quantities.containsKey(key) && quantities.get(key) != null && !quantities.get(key).isEmpty()) {
                parsedQuantities.add(Integer.parseInt(quantities.get(key)));
                filteredProductIds.add(productIds.get(i));
            }
        }


        log.info("parsedQuantities in create order received: {}", parsedQuantities);
        log.info("productIds in create order received: {}", productIds);

        //將使用者輸入的數量以及產品id傳給service，做新增
        for (int i = 0; i < filteredProductIds.size(); i++) {
            productService.getAllProductByProductIDtry(parsedQuantities.get(i), filteredProductIds.get(i));
        }

        model.addAttribute("orderdetails", orderdetailService.getAllByorderdetail());
        return "orderList";
    }
}
