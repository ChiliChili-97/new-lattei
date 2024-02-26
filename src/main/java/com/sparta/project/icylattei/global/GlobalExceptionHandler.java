package com.sparta.project.icylattei.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //request body의 데이터가 유효하지 않을 때 에러를 리턴
    public ResponseEntity<ExceptionDto> handleMethodArgumentNoValidException(
        MethodArgumentNotValidException e) {
        return createResponse(HttpStatus.BAD_REQUEST,
            e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity handleException(Exception e) {
        return createResponse(HttpStatus.BAD_REQUEST,
            e.getMessage());
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
