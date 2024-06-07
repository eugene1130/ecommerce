package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.entity.order;
import com.example.ecommercespringboot.entity.orderdetail;
import com.example.ecommercespringboot.entity.product;
import com.example.ecommercespringboot.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class productService {

    private static final Logger log = LoggerFactory.getLogger(productService.class);

    @Autowired
    private productRepository productRepository;

    @Autowired
    private orderService orderService;

    @Autowired
    private com.example.ecommercespringboot.repository.orderdetailRepository orderdetailRepository;

    public List<product> getAllProducts() {//取得所有產品
        return productRepository.findAll();
    }

    public List<product> getAllProductsNonNull() {//取得大於0的產品
        return productRepository.findAll().stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

    }

    public void getAllProductByProductIDtry(Integer quantities,String productID) {

        //抓產品和訂單並儲存
        List<product> productList = new ArrayList<>();

            product product = productRepository.findByproductid(productID);
            log.info("product received: {}", product);
            if (product != null) {
                productList.add(product);
            }

        List<order> orderList = orderService.getAllOrders();
        log.info("orderList received: {}", orderList);

        for (int i = 0; i < productList.size(); i++) {
            order order = orderList.get(i);
            log.info("order received: {}", order);
            log.info("product received: {}", product);
            log.info("quantity received: {}", quantities);
            int total = quantities * product.getPrice();
            log.info("total received: {}", total);

            //將資訊傳給以下funciton以儲存訂單
            saveOrderdetail(order.getOrderid(),product.getProductid(),quantities,product.getPrice(),total);

            log.info("Orderid received: {}", order.getOrderid());
            log.info("Productid received: {}", product.getProductid());
            log.info("Quantity received: {}", quantities);
            log.info("Standprice received: {}", product.getPrice());
            log.info("Itemprice received: {}", total);
        }

    }

    public void saveProduct(product product) {//儲存產品
        productRepository.save(product);
    }

    public void saveOrderdetail(String order,String poroductid,int quantity,int poroductPrice,int total ) {

        //儲存訂單
        orderdetail orderdetail=new orderdetail();
        orderdetail.setOrderid(order);
        orderdetail.setProductid(poroductid);
        orderdetail.setQuantity(quantity);
        orderdetail.setStandprice(poroductPrice);
        orderdetail.setItemprice(total);

        log.info("Orderid received: {}", orderdetail.getOrderid());
        log.info("Productid received: {}", orderdetail.getProductid());
        log.info("Quantity received: {}", orderdetail.getQuantity());
        log.info("Standprice received: {}", orderdetail.getStandprice());
        log.info("Itemprice received: {}", orderdetail.getItemprice());
        orderdetailRepository.save(orderdetail);

    }

}
