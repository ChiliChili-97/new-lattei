package com.sparta.project.icylattei.cart.repository;

import static com.sparta.project.icylattei.user.entity.UserRoleEnum.USER;
import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.product.repository.ProductRepository;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.repository.UserRepository;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

@ActiveProfiles("Repository test")
@DisplayName("Cart Repository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @DisplayName("장바구니 저장")
    @Test
    void createCartTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        CartRequestDto requestDto = new CartRequestDto("아아", 3, "장바구니");
        Product product = productRepository.findByProductName(requestDto.getProductName());
        Cart beforeCart = new Cart(product, requestDto, user);
        //when
        userRepository.save(user);
        Cart saveCart = cartRepository.save(beforeCart);
        //then
        assertThat(saveCart).isEqualTo(beforeCart);
    }

    @DisplayName("장바구니 삭제")
    @Test
    void deleteCartTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        CartRequestDto requestDto = new CartRequestDto("아아", 3, "장바구니");
        Product product = productRepository.findByProductName(requestDto.getProductName());
        List<Cart> beforeCart = cartRepository.findAll();
        userRepository.save(user);
        //when
        Cart saveCart = cartRepository.save(new Cart(product, requestDto, user));
        cartRepository.delete(saveCart);
        List<Cart> afterCart = cartRepository.findAll();
        //then
        assertThat(afterCart).isEqualTo(beforeCart);
    }

    @DisplayName("장바구니 수정")
    @Test
    void updateCartTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        userRepository.save(user);

        CartRequestDto beforeRequestDto = new CartRequestDto("아아", 3, "장바구니");
        Product beforeProduct = productRepository.findByProductName(
            beforeRequestDto.getProductName());
        Cart cart = cartRepository.save(new Cart(beforeProduct, beforeRequestDto, user));

        //when
        CartRequestDto afterRequestDto = new CartRequestDto("아아", 2, "장바구니");
        Product afterProduct = productRepository.findByProductName(
            afterRequestDto.getProductName());
        cart.update(afterProduct, afterRequestDto);

        //then
        assertThat(cart.getCartStatus()).isEqualTo(afterRequestDto.getCartStatus());
        assertThat(cart.getQuantity()).isEqualTo(afterRequestDto.getQuantity());
        assertThat(cart.getProduct()).isEqualTo(afterProduct);
    }

    @DisplayName("장바구니 조회")
    @Test
    void getCartTest() {
        //given
        User user = new User("abc12345", "abc@12345", USER, "abc12345");
        userRepository.save(user);
        CartRequestDto beforeRequestDto = new CartRequestDto("아아", 3, "장바구니");
        Product beforeProduct = productRepository.findByProductName(
            beforeRequestDto.getProductName());
        Cart cart = cartRepository.save(new Cart(beforeProduct, beforeRequestDto, user));
        Long cartId = cart.getCartId();
        //when
        Cart savedCart = cartRepository.findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("장바구니가 없습니다."));

        //then
        assertThat(cart).isEqualTo(savedCart);

    }

}
