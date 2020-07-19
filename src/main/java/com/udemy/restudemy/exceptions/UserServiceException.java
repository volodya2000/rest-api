package com.udemy.restudemy.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID=1348771109171435607L;

    public UserServiceException(String message) {
        super(message);
    }
}
