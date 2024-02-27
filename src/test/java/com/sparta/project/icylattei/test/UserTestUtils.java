package com.sparta.project.icylattei.test;

import com.sparta.project.icylattei.user.entity.User;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.SerializationUtils;

public class UserTestUtils {

    public static User get(User user) {
        return get(user, 1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public static User get(User user, Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        User newUser = SerializationUtils.clone(user);

        ReflectionTestUtils.setField(newUser, User.class, "id", id, Long.class);
        ReflectionTestUtils.setField(newUser, User.class, "createdAt", createdAt,
            LocalDateTime.class);
        ReflectionTestUtils.setField(newUser, User.class, "modifiedAt", modifiedAt,
            LocalDateTime.class);

        return newUser;
    }
}
