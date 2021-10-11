package com.codemark.codemark.exceptionhandler;

//Исключения при валидации данных пользователя

public class UserSaveException extends RuntimeException{
    public UserSaveException(String message) {
        super(message);
    }
}
