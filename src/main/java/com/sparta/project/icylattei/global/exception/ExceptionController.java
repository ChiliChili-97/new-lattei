package com.sparta.project.icylattei.global.exception;

import com.sparta.project.icylattei.global.dto.ExceptionDto;
import com.sparta.project.icylattei.global.exception.custom.NotMatchedPassword;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.persistence.EntityExistsException;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return createResponse(HttpStatus.BAD_REQUEST,
            Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler({
        IllegalArgumentException.class,
        UsernameNotFoundException.class,
        AuthenticationServiceException.class,
        NoSuchElementException.class,
        NotMatchedPassword.class,
        EntityExistsException.class
    })
    public ResponseEntity<ExceptionDto> BadRequestExceptionHandler(Exception e) {
        return createResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({
        SecurityException.class,
        MalformedJwtException.class,
        ExpiredJwtException.class,
        UnsupportedJwtException.class,
    })
    public ResponseEntity<ExceptionDto> ForbiddenExceptionHandler(Exception e) {
        return createResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<ExceptionDto> handleDuplicateException(Exception e) {
        return createResponse(HttpStatus.CONFLICT,
            e.getMessage());
    }

    private ResponseEntity<ExceptionDto> createResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status.value())
            .body(ExceptionDto.builder()
                .statusCode(status.value())
                .state(status)
                .message(message)
                .build());
    }
}
