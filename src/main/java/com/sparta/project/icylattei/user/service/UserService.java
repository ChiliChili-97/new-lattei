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

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signup(SignupRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());
        String nickname = request.getNickname();

        // 중복된 사용자 확인
        validateUserDuplicate(userRepository.findByUsername(username));
        // 사용자 ROLE 확인
        UserRoleEnum role =  UserRoleEnum.USER;
        role = validateUserRole(request, role);

        User user = new User(username, password, role, nickname);
        userRepository.save(user);
    }

    private void validateUserDuplicate(Optional<User> checkUsername) {
        if (checkUsername.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    private UserRoleEnum validateUserRole(SignupRequest request, UserRoleEnum role) {
        if (request.isAdmin()) {
            if (!ADMIN_TOKEN.equals(request.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        return role;

    }
}