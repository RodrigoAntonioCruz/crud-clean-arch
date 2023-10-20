package com.example.core.exception;

import com.example.core.utils.Constants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND);
    }
}
