package com.sparta.project.icylattei.order.repository;

import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
