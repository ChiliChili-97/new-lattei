package com.sparta.project.icylattei.order.repository;

import static com.sparta.project.icylattei.user.entity.UserRoleEnum.USER;
import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.order.dto.OrderRequestDto;
import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.List;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("Repository test")
@DisplayName("Order Repository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("주문 저장")
    @Test
    void createOrderTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        CartRequestDto requestDto = new CartRequestDto(1L, 3);
        Product product = productRepository.findById(requestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Cart beforeCart = new Cart(product, requestDto, user, "장바구니");
        userRepository.save(user);
        cartRepository.save(beforeCart);
        //when
        List<Cart> carts = cartRepository.findAllByUser(user);
        Order order = orderRepository.save(new Order(carts, user));
        //then
//        assertThat(order.getCarts()).isEqualTo(carts);
        assertThat(order.getUser()).isEqualTo(user);
    }

    @DisplayName("주문 조회")
    @Test
    void getOrderTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        userRepository.save(user);
        CartRequestDto beforeRequestDto = new CartRequestDto(1L, 3);
        Product beforeProduct = productRepository.findById(
                beforeRequestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Cart cart = cartRepository.save(new Cart(beforeProduct, beforeRequestDto, user, "장바구니"));
        List<Cart> carts = cartRepository.findAllByUser(user);
        Order order = orderRepository.save(new Order(carts, user));
        //when
        Order savedOrder = orderRepository.findById(order.getOrderId())
            .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        //then
        assertThat(order).isEqualTo(savedOrder);
    }

    @DisplayName("주문 수정")
    @Test
    void updateOrderTest(){
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        userRepository.save(user);
        CartRequestDto beforeRequestDto = new CartRequestDto(1L, 3);
        Product beforeProduct = productRepository.findById(
                beforeRequestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        cartRepository.save(new Cart(beforeProduct, beforeRequestDto, user, "장바구니"));
        List<Cart> carts = cartRepository.findAllByUser(user);
        Order order = orderRepository.save(new Order(carts, user));
        CartRequestDto afterRequestDto = new CartRequestDto(2L, 1);
        Product afterProduct = productRepository.findById(
                afterRequestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        cartRepository.save(new Cart(afterProduct, afterRequestDto, user, "장바구니"));
        List<Cart> cartss = cartRepository.findAllByUser(user);
        //when
        order.update(cartss);
        //then
        assertThat(cartss).isEqualTo(order.getCarts());
    }

    @DisplayName("주문 삭제")
    @Test
    void deleteOrderTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        CartRequestDto requestDto = new CartRequestDto(1L, 3);
        Product product = productRepository.findById(requestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        userRepository.save(user);
        List<Order> beforeOrder = orderRepository.findAll();
        Cart saveCart = cartRepository.save(new Cart(product, requestDto, user, "장바구니"));
        List<Cart> beforeCart = cartRepository.findAll();
        Order savedOrder = orderRepository.save(new Order(beforeCart, user));
        //when
        orderRepository.delete(savedOrder);
        List<Order> afterOrder = orderRepository.findAll();
        //then
        assertThat(afterOrder).isEqualTo(beforeOrder);

    }
}
