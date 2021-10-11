package com.codemark.codemark.exceptionhandler;

//Класс, который будет возвращать ответ в формате json при добавлении и редактировании данных
public class Response {
    private boolean success;
    private String errors;

    public Response() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
