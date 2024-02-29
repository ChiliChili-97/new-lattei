package com.sparta.project.icylattei.order.service;

import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.order.repository.OrderRepository;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

}
