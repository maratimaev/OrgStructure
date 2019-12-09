package ru.homework.dto.result;

/**
 * Сообщение об успешном выполнении запроса
 */
public class Success {
    private String result;

    public Success() {
        this.result = "success";
    }

    public String getResult() {
        return result;
    }
}
