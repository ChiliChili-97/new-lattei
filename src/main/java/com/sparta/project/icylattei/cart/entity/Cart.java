package com.sparta.project.icylattei.cart.entity;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.product.entity.Product;
import com.sparta.project.icylattei.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String cartStatus;

    public Cart(Product product, CartRequestDto requestDto, User user) {
        this.user = user;
        this.product = product;
        this.quantity = requestDto.getQuantity();
        this.cartStatus = requestDto.getCartStatus();
    }

    public void update(Product product, CartRequestDto requestDto) {
        this.product = product;
        this.quantity = requestDto.getQuantity();
        this.cartStatus = requestDto.getCartStatus();
    }
}
