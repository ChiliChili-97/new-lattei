package com.sparta.project.icylattei.user.controller;

import com.sparta.project.icylattei.user.dto.requestDto.PasswordUpdateRequest;
import com.sparta.project.icylattei.user.dto.requestDto.ProfileRequest;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.dto.responseDto.ProfileResponse;
import com.sparta.project.icylattei.user.service.UserService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {

        userService.signup(request);
    }

/*
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
            SecurityContextHolder.getContext().getAuthentication());
    }
*/

    @GetMapping
    public ProfileResponse getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getProfile(userDetails);
    }

    @PutMapping
    public ProfileResponse updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody ProfileRequest request) {
        return userService.updateProfile(userDetails, request);
    }

    @PutMapping("/password")
    public void updatePassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody PasswordUpdateRequest request) {

        userService.updatePassword(userDetails, request);
    }
}