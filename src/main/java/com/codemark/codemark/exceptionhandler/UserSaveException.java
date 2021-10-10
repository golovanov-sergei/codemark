package com.codemark.codemark.exceptionhandler;

public class UserSaveException extends RuntimeException{
    public UserSaveException(String message) {
        super(message);
    }
}
