package ru.homework.dto.profile;

/**
 * Профили @Validated для входящих json объектов
 */
public interface InputProfile {

    /**
     * Проверка корректности полей при создании объекта
     */
    interface Create { }

    /**
     * Проверка корректности полей при обновлении объекта
     */
    interface Update {}

    /**
     * Проверка корректности полей при удалении объекта
     */
    interface Dismiss{}

    /**
     * Проверка корректности поля при поиске департамента по имени
     */
    interface Name {}
}
