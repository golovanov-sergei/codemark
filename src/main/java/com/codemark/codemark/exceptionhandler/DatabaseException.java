package com.codemark.codemark.exceptionhandler;

//Исключение для обработки ситуаций при записи в БД

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
