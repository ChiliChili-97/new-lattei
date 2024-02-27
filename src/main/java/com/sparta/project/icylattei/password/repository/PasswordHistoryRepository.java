package com.sparta.project.icylattei.password.repository;

import com.sparta.project.icylattei.password.entity.PasswordHistory;
import com.sparta.project.icylattei.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
    List<PasswordHistory> findTop3ByUserOrderByCreatedAtDesc(User user);

}
