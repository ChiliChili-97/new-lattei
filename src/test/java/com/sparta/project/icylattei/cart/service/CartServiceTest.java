package com.sparta.project.icylattei.cart.service;


import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;
}
