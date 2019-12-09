package ru.homework.exception;

/**
 * Обертка для вывода ошибок
 */
public class CustomException extends RuntimeException {
    private String param;

    private RuntimeException ex;

    public CustomException() {
    }

    public CustomException(String param) {
        this.param = param;
    }

    public CustomException(String param, RuntimeException ex) {
        this.param = param;
        this.ex = ex;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public RuntimeException getEx() {
        return ex;
    }

    public void setEx(RuntimeException ex) {
        this.ex = ex;
    }
}
