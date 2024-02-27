package com.sparta.project.icylattei.order.entity;


import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.order.dto.OrderRequestDto;
import com.sparta.project.icylattei.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToMany
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    private User user;

    @Column(nullable = true)
    private Date orderDate;

    public Order(List<Cart> carts, User user) {
        this.carts = carts;
        this.user = user;
    }

    public void update(List<Cart> carts) {
        this.carts = carts;
    }
}
