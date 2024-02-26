package com.sparta.project.icylattei.user.service;

import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());

        validateUserDuplicate(userRepository.findByUsername(username));
        User user = new User(username, password, UserRoleEnum.USER);

        userRepository.save(user);
    }

    private void validateUserDuplicate(Optional<User> checkUsername) {
        if (checkUsername.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }
}