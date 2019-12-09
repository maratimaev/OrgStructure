package ru.homework.dto.result;

/**
 * Обертка для сообщения об ошибке
 */
public class Error {
    private String error;

    public Error() {
    }

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
