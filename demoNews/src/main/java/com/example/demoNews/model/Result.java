package com.example.demoNews.model;

public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "Success", data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(false, message, null);
    }

    public static <T> Result<T> failure(String message, T data) {
        return new Result<>(false, message, data);
    }
}

