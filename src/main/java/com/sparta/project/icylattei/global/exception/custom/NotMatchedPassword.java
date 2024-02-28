package com.sparta.project.icylattei.global.exception.custom;

public class NotMatchedPassword extends RuntimeException {

    public NotMatchedPassword(String message) {
        super(message);
    }
}
