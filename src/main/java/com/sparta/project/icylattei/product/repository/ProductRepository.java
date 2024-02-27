package com.sparta.project.icylattei.product.repository;

import com.sparta.project.icylattei.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
}
