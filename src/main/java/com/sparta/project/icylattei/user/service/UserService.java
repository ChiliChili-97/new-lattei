package com.sparta.project.icylattei.user.service;

import com.sparta.project.icylattei.password.entity.PasswordHistory;
import com.sparta.project.icylattei.password.repository.PasswordHistoryRepository;
import com.sparta.project.icylattei.user.dto.requestDto.PasswordUpdateRequest;
import com.sparta.project.icylattei.user.dto.requestDto.ProfileRequest;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.dto.responseDto.ProfileResponse;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.repository.UserRepository;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import java.util.List;
import java.util.NoSuchElementException;
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
    private final PasswordHistoryRepository passwordHistoryRepository;
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

    // 프로필 조회
    public ProfileResponse getProfile(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("사용자가 존재하지 않습니다."));

        return new ProfileResponse(user);
    }

    // 프로필 수정
    @Transactional
    public ProfileResponse updateProfile(UserDetailsImpl userDetails, ProfileRequest request) {
        Long userId = userDetails.getUser().getId();

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("사용자가 존재하지 않습니다"));

        user.update(request.getNickname(), request.getInfo());

        return new ProfileResponse(user);
    }

    // 비밀번호 변경
    @Transactional
    public void updatePassword(UserDetailsImpl userDetails, PasswordUpdateRequest request) {
        Long userId = userDetails.getUser().getId();

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("사용자가 존재하지 않습니다."));

        String currentPassword = user.getPassword();
        String newPassword = request.getPassword();

        validateNewPassword(newPassword, currentPassword);

        List<PasswordHistory> recentPassword =
            passwordHistoryRepository.findTop3ByUserOrderByCreatedAtDesc(user);

        if(recentPassword.isEmpty()) {
            PasswordHistory passwordHistory = new PasswordHistory(currentPassword, user);

            passwordHistoryRepository.save(passwordHistory);

            user.updatePassword(passwordEncoder.encode(request.getPassword()));
        }

        if(!recentPassword.isEmpty()) {
           for(PasswordHistory password: recentPassword) {
               if(passwordEncoder.matches(newPassword, password.getPastPassword())) {
                   throw new DuplicateKeyException("사용했던 비밀번호입니다. 새로운 비밀번호를 입력해주세요.");
               }
           }
        }







    }

    private void validateNewPassword(String newPassword, String currentPassword) {
        if(passwordEncoder.matches(newPassword, currentPassword))
            throw new IllegalArgumentException("기존 비밀번호와 동일합니다.");

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