package com.sparta.project.icylattei.cart.repository;

import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserAndCartStatus(User user, String cartStatus);
}
